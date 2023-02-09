package duke.filemanager;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import duke.task.Task;

import java.io.FileWriter;
import java.io.Writer;

public class TaskWriter {
    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public TaskWriter() {

    }

    public void writeToJson(Task[] taskList) {
        try {
            Writer writer = new FileWriter("duke.json");
            gson.toJson(taskList, writer);
            writer.close();
        } catch (Exception e) {
            System.out.println("Failed to write to file");
        }

    }

}
