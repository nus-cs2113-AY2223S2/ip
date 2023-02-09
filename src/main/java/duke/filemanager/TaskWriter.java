package duke.filemanager;

import com.google.gson.*;
import duke.task.Task;

import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;

public class TaskWriter {
    public void writeToJson(ArrayList<Task> taskList) {
        try {
            Writer writer = new FileWriter("duke.json");
            JsonArray jsonArray = new Gson().toJsonTree(taskList).getAsJsonArray();
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("taskList", jsonArray);
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            writer.write(gson.toJson(jsonObject));
            writer.close();
        } catch (Exception e) {
            System.out.println("Failed to write to file");
        }

    }

}
