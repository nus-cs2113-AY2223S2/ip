package duke;

import com.google.gson.*;
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

    //adapted from https://www.tutorialspoint.com/how-to-add-insert-additional-property-to-json-string-using-gson-in-java#:~:text=We%20can%20use%20the%20toJsonTree,get%20the%20element%20as%20JsonObject.
    public Storage () {
        this.gson = new Gson();
    }
    public void saveData(TaskList tasks) throws IOException {
        File file = new File(filePath);

        if (file.exists()) {
            file.delete();
        }

        file.createNewFile();
        taskToJSON(tasks, file);
    }
    public void taskToJSON(TaskList tasks, File file) {
        JsonArray jsonArray = new JsonArray();
        try {
            FileWriter input = new FileWriter(file);

            for (Task task : tasks) {
                JsonElement jsonElement = gson.toJsonTree(task);
                jsonElement.getAsJsonObject().addProperty("type", task.getClass().getSimpleName());
                jsonArray.add(jsonElement);

            }

            System.out.println(gson.toJson(jsonArray));
            gson.toJson(jsonArray, input);
            input.close();

        } catch (IOException e) {
            System.out.println("Error saving file");
        } catch (JsonIOException e) {
            System.out.println(e);
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
            System.out.println("No tasks to load");
        }
    }
}
