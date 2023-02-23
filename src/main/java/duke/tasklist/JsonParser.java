package duke.tasklist;

import com.google.gson.*;
import duke.parser.DateTimeParser;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class JsonParser {
    private static final GsonBuilder builder = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class,
                                 (JsonSerializer<LocalDateTime>) (json, type, jsonDeserializationContext)
                                         -> new JsonPrimitive(json.format(DateTimeParser.getFormatter())))
            .registerTypeAdapter(LocalDateTime.class,
                                 (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext)
                                         -> LocalDateTime.parse(json.getAsJsonPrimitive().getAsString(),
                                                                DateTimeParser.getFormatter()))
            .create().newBuilder();
    static final Gson gson = builder.create();
    private static final String TASK_TODO = "TODO";
    private static final String TASK_DEADLINE = "DEADLINE";
    private static final String TASK_EVENT = "EVENT";

    public static ArrayList<Task> fromJson(String json) {
        Scanner scanner = new Scanner(json);
        ArrayList<Task> savedTasks = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String task = scanner.nextLine();
            savedTasks.add(deserializeJSON(task));
        }
        return savedTasks;
    }

    public static String toJson(ArrayList<Task> tasks) {
        StringBuilder saveData = new StringBuilder();
        for (Task t : tasks) {
            String json = gson.toJson(t);
            saveData.append(json);
            saveData.append(System.lineSeparator());
        }
        return saveData.toString();
    }

    public static Task deserializeJSON(String json) {
        JsonObject j = gson.fromJson(json, JsonObject.class);
        Task t;
        String taskType = j.get("type").getAsString();
        switch (taskType) {
        case TASK_TODO:
            t = gson.fromJson(j, ToDo.class);
            break;
        case TASK_EVENT:
            t = gson.fromJson(j, Event.class);
            break;
        case TASK_DEADLINE:
            t = gson.fromJson(j, Deadline.class);
            break;
        default:
            t = gson.fromJson(j, Task.class);
        }
        return t;
    }
}
