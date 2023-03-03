package duke.parser.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import duke.exceptions.CorruptSaveDataException;
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
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create().newBuilder();
    static final Gson GSON = GSON_BUILDER.create();
    private static final String TASK_DEADLINE = "DEADLINE";
    private static final String TASK_EVENT = "EVENT";
    private static final String TASK_TODO = "TODO";

    /**
     * Convert the JSON string into the corresponding task. If the data is corrupted, a generic Task object is returned.
     *
     * @param taskJson Task to be deserialized
     * @return Task object corresponding to the JSON string
     */
    public static Task deserializeJSON(String taskJson) throws CorruptSaveDataException {
        Task savedTask;
        try {
            JsonObject jsonObject = GSON.fromJson(taskJson, JsonObject.class);
            JsonElement taskType = jsonObject.get("type");
            if (taskType == null) {
                throw new CorruptSaveDataException(taskJson);
            }

            switch (taskType.getAsString()) {
            case TASK_TODO:
                savedTask = GSON.fromJson(jsonObject, ToDo.class);
                break;
            case TASK_EVENT:
                savedTask = GSON.fromJson(jsonObject, Event.class);
                break;
            case TASK_DEADLINE:
                savedTask = GSON.fromJson(jsonObject, Deadline.class);
                break;
            default:
                throw new CorruptSaveDataException(taskJson);
            }
        } catch (JsonSyntaxException e) {
            throw new CorruptSaveDataException(taskJson);
        }
        return savedTask;
    }

    /**
     * Deserializes the given string in JSON format.
     *
     * @param tasksJson Tasks to be deserialized
     * @return ArrayList containing Task objects corresponding to the saved data
     */
    public static ArrayList<Task> fromJson(String tasksJson) throws CorruptSaveDataException {
        Scanner scanner = new Scanner(tasksJson);
        ArrayList<Task> savedTasks = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String taskJson = scanner.nextLine();
            savedTasks.add(deserializeJSON(taskJson));
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
        for (Task task : tasks) {
            String taskJson = GSON.toJson(task);
            saveData.append(taskJson);
            saveData.append(System.lineSeparator());
        }
        return saveData.toString();
    }
}
