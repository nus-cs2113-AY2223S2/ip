package duke.filemanager;

import com.google.gson.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.io.FileReader;
import java.io.Reader;

public class TaskLoader {

    public Task[] setClasses() {
        Task[] tasks = new Task[3];
        Gson gson = new Gson();
        JsonArray jsonArray = new JsonArray();
        try {
            Reader reader = new FileReader("duke.json");
            jsonArray = gson.fromJson(reader, JsonArray.class);
        } catch (Exception e) {
            System.out.println(e);
        }
        int count = 0;
        for (JsonElement element : jsonArray) {
            if (element.getAsJsonObject().has("type") && element.getAsJsonObject().get("type").getAsString().equals("todo")) {
                tasks[count] = gson.fromJson(element, Task.class);
            } else if (element.getAsJsonObject().has("startTime")) {
                tasks[count] = gson.fromJson(element, Event.class);
            } else if (element.getAsJsonObject().has("dueBy")) {
                tasks[count] = gson.fromJson(element, Deadline.class);
            }
            count++;
        }
        return tasks;
    }


}
