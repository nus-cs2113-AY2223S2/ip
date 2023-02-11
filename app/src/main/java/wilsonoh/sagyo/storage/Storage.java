package wilsonoh.sagyo.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import wilsonoh.sagyo.exceptions.InvalidTaskException;
import wilsonoh.sagyo.tasklist.TaskList;
import wilsonoh.sagyo.tasks.DeadlineTask;
import wilsonoh.sagyo.tasks.EventTask;
import wilsonoh.sagyo.tasks.Task;
import wilsonoh.sagyo.tasks.TodoTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;

public class Storage {

    private Path taskListPath;

    public Storage() {
        Path xdgConfigHome;
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            xdgConfigHome = Path.of(System.getenv("LOCALAPPDATA"));
        } else {
            xdgConfigHome = Path.of(System.getenv("HOME"), ".config");
        }
        Path sagyoHome = xdgConfigHome.resolve("sagyo");
        if (!Files.exists(sagyoHome)) {
            try {
                Files.createDirectory(sagyoHome);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        taskListPath = sagyoHome.resolve("tasks.json");
    }

    public void writeTasksToJSON(TaskList tasks) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonArray jsonArray = new JsonArray();
        for (Task task : tasks) {
            JsonElement element = gson.toJsonTree(task);
            element.getAsJsonObject().addProperty("task_type", task.getClass().getSimpleName());
            jsonArray.add(element);
        }
        try {
            Files.writeString(taskListPath, gson.toJson(jsonArray));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> getTaskListFromJSON() throws InvalidTaskException {
        Gson gson = new Gson();
        ArrayList<Task> ret = new ArrayList<>();
        try {
            JsonArray arr = gson.fromJson(Files.readString(taskListPath), JsonArray.class);
            for (JsonElement element : arr) {
                String taskType = element.getAsJsonObject().get("task_type").getAsString();
                Task toAdd;
                switch (taskType) {
                case "TodoTask":
                    toAdd = gson.fromJson(element, TodoTask.class);
                    break;
                case "EventTask":
                    toAdd = gson.fromJson(element, EventTask.class);
                    break;
                case "DeadlineTask":
                    toAdd = gson.fromJson(element, DeadlineTask.class);
                    break;
                default:
                    throw new InvalidTaskException("Invalid task type");
                }
                ret.add(toAdd);
            }
        } catch (JsonSyntaxException | IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
