package duke.tasklist;

import com.google.gson.*;
import duke.exceptions.CorruptSaveDataException;
import duke.parser.DateTimeParser;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Serializes and deserializes tasks using the Gson library.
 */
public class JsonParser {
    private static final GsonBuilder GSON_BUILDER = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class,
                    (JsonSerializer<LocalDateTime>) (json, type, jsonDeserializationContext)
                            -> new JsonPrimitive(json.format(DateTimeParser.getFormatter())))
            .registerTypeAdapter(LocalDateTime.class,
                    (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext)
                            -> LocalDateTime.parse(json.getAsJsonPrimitive().getAsString(),
                            DateTimeParser.getFormatter()))
            .create().newBuilder();
    static final Gson GSON = GSON_BUILDER.create();
    private static final String TASK_DEADLINE = "DEADLINE";
    private static final String TASK_EVENT = "EVENT";
    private static final String TASK_TODO = "TODO";

    /**
     * Convert the JSON string into the corresponding task. If the data is corrupted, a generic Task object is returned.
     *
     * @param json Task to be deserialized
     * @return Task object corresponding to the JSON string
     */
    public static Task deserializeJSON(String json) throws CorruptSaveDataException {
        Task t;
        try {
            JsonObject j = GSON.fromJson(json, JsonObject.class);
            JsonElement taskType = j.get("type");
            if (taskType == null) {
                throw new CorruptSaveDataException(json);
            }

            switch (taskType.getAsString()) {
            case TASK_TODO:
                t = GSON.fromJson(j, ToDo.class);
                break;
            case TASK_EVENT:
                t = GSON.fromJson(j, Event.class);
                break;
            case TASK_DEADLINE:
                t = GSON.fromJson(j, Deadline.class);
                break;
            default:
                throw new CorruptSaveDataException(json);
            }
        } catch (JsonSyntaxException e) {
            throw new CorruptSaveDataException(json);
        }
        return t;
    }

    /**
     * Deserializes the given string in JSON format.
     *
     * @param json Tasks to be deserialized
     * @return ArrayList containing Task objects corresponding to the saved data
     */
    public static ArrayList<Task> fromJson(String json) throws CorruptSaveDataException {
        Scanner scanner = new Scanner(json);
        ArrayList<Task> savedTasks = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String task = scanner.nextLine();
            savedTasks.add(deserializeJSON(task));
        }
        return savedTasks;
    }

    /**
     * Serializes the given tasks into JSON format.
     *
     * @param tasks Tasks to be serialized
     * @return String containing serialized tasks in JSON format
     */
    public static String toJson(ArrayList<Task> tasks) {
        StringBuilder saveData = new StringBuilder();
        for (Task t : tasks) {
            String json = GSON.toJson(t);
            saveData.append(json);
            saveData.append(System.lineSeparator());
        }
        return saveData.toString();
    }
}
