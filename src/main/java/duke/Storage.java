package duke;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Saves and loads the task list stored in the user's device.
 */
public class Storage {
    private Gson gson;
    private static String folder = "data";
    private static String filePath = "data/tasks.json";

    /**
     * Constructs a Storage object.
     */
    public Storage() {
        this.gson = new Gson();
    }

    /**
     * Saves the list of tasks entered by the user.
     * @param tasks List of tasks entered by the user
     * @param ui Prints warning message if task list cannot be converted to JSON
     * @throws IOException When file cannot be saved
     */
    public void saveData(TaskList tasks, UI ui) throws IOException {
        File file = new File(filePath);

        if (file.exists()) {
            file.delete();
        }

        file.createNewFile();
        taskToJson(tasks, file, ui);
    }

    /**
     * Converts the list of tasks entered by the user into a JSON file
     * @param tasks List of tasks entered by the user.
     * @param file File for tasks to be saved in.
     * @param ui Prints error message when tasks cannot be converted to JSON or saved.
     */
    public void taskToJson(TaskList tasks, File file, UI ui) {
        JsonArray jsonArray = new JsonArray();
        try {
            FileWriter fileWriter = new FileWriter(file);

            for (Task task : tasks) {
                JsonElement jsonElement = gson.toJsonTree(task);
                jsonElement.getAsJsonObject().addProperty("type", task.getClass().getSimpleName());
                jsonArray.add(jsonElement);
            }
            gson.toJson(jsonArray, fileWriter);
            fileWriter.close();
        } catch (IOException | JsonIOException e) {
            ui.printSavingError();
        }
    }

    /**
     * Loads previously-entered tasks from a JSON file that was previously saved by the user
     * @param tasks List of tasks entered by the user
     * @param ui Prints error message
     * @throws IOException When JSON file cannot be loaded
     */
    public void loadData(TaskList tasks, UI ui) throws IOException {
        File directory = new File(folder);

        if (!(directory.isDirectory() && directory.exists())) {
            new File(folder).mkdirs();
        }

        File file = new File(filePath);

        if (!file.exists()) {
            file.createNewFile();
        }

        Scanner scanner = new Scanner(file);
        FileReader fileReader = new FileReader(filePath);
        Gson gson = new Gson();
        JsonArray tempList = gson.fromJson(fileReader, JsonArray.class);
        jsonToTask(tasks, tempList, ui);
    }

    /**
     * Converts JSON file back into TaskList object
     * @param tasks List of tasks that tasks from the JSON file are added to
     * @param tempList Tasks from the saved JSON file
     * @param ui Prints warning message when there is no file to be loaded.
     */
    public void jsonToTask(TaskList tasks, JsonArray tempList, UI ui) {
        try {
            for (JsonElement element : tempList) {
                String type = element.getAsJsonObject().get("type").getAsString();
                Task taskToAdd = gson.fromJson(element, Task.class);
                switch (type) {
                case "Todo":
                    taskToAdd = gson.fromJson(element, Todo.class);
                    break;
                case "Deadline":
                    taskToAdd = gson.fromJson(element, Deadline.class);
                    break;
                case "Event":
                    taskToAdd = gson.fromJson(element, Event.class);
                    break;
                default:
                    ui.printInvalidFileRead();
                }
                tasks.addTask(taskToAdd);
            }
        } catch (NullPointerException e) {
            ui.printNoTasksToLoad();
        }
    }
}
