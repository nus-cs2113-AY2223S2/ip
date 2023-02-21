package duke.filemanager;

import com.google.gson.*;
import duke.exceptions.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskLoader {

    public ArrayList<Task> setClasses(String filePath) throws DukeException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder
                .registerTypeAdapter(LocalDateTime.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();
        JsonArray jsonArray;
        try {
            Reader reader = new FileReader(filePath);
            JsonParser parser = new JsonParser();
            JsonElement jsonTree = parser.parse(reader);
            jsonArray = jsonTree.getAsJsonObject().getAsJsonArray("taskList");
            for (JsonElement element : jsonArray) {
                if (element.getAsJsonObject().has("type") && element.getAsJsonObject().get("type").getAsString().equals("todo")) {
                    tasks.add(gson.fromJson(element, Task.class));
                } else if (element.getAsJsonObject().has("startTime")) {
                    tasks.add(gson.fromJson(element, Event.class));
                } else if (element.getAsJsonObject().has("dueBy")) {
                    tasks.add(gson.fromJson(element, Deadline.class));
                }
            }
        } catch (Exception e) {
            throw new DukeException("File corrupted!");
        }
        return tasks;
    }


}
