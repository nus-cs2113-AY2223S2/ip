package duke.filemanager;

import com.google.gson.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

public class TaskLoader {

    public ArrayList<Task> setClasses() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        Gson gson = new Gson();
        JsonArray jsonArray = new JsonArray();
        try {
            Reader reader = new FileReader("duke.json");
            JsonParser parser = new JsonParser();
            JsonElement jsonTree = parser.parse(reader);
            jsonArray = jsonTree.getAsJsonObject().getAsJsonArray("taskList");
        } catch (FileNotFoundException notFound) {
            System.out.println("Data file does not exist, starting fresh");
        } catch (IllegalStateException i) {
            System.out.println("Corrupted data file, starting fresh");
        } catch (Exception e) {
            System.out.println("Unexpected error occurred");
            System.out.println(e);
        }
        for (JsonElement element : jsonArray) {
            if (element.getAsJsonObject().has("type") && element.getAsJsonObject().get("type").getAsString().equals("todo")) {
                tasks.add(gson.fromJson(element, Task.class));
            } else if (element.getAsJsonObject().has("startTime")) {
                tasks.add(gson.fromJson(element, Event.class));
            } else if (element.getAsJsonObject().has("dueBy")) {
                tasks.add(gson.fromJson(element, Deadline.class));
            }
        }
        return tasks;
    }


}
