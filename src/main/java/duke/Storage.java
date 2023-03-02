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

public class Storage {
    private Gson gson;
    private static String folder = "data";
    private static String filePath = "data/tasks.json";

    public Storage() {
        this.gson = new Gson();
    }

    public void saveData(TaskList tasks) throws IOException {
        File file = new File(filePath);

        if (file.exists()) {
            file.delete();
        }

        file.createNewFile();
        taskToJson(tasks, file);
    }

    public void taskToJson(TaskList tasks, File file) {
        JsonArray jsonArray = new JsonArray();
        try {
            FileWriter input = new FileWriter(file);

            for (Task task : tasks) {
                JsonElement jsonElement = gson.toJsonTree(task);
                jsonElement.getAsJsonObject().addProperty("type", task.getClass().getSimpleName());
                jsonArray.add(jsonElement);
            }
            gson.toJson(jsonArray, input);
            input.close();
        } catch (IOException | JsonIOException e) {
            System.out.println("Error saving file");
        }
    }

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
            System.out.println("There are no tasks to load");
        }
    }
}
