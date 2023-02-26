package tusky.storage;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import tusky.tasks.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    private final String filePath;


    private final Gson gson;

    public Storage (String filePath) {

        this.filePath = filePath;

        GsonBuilder gsonBuilder = new GsonBuilder();
        JsonDeserializer<Task> deserializer = buildTaskDeserializer();
        gsonBuilder.registerTypeAdapter(Task.class, deserializer);
        this.gson = gsonBuilder.create();
    }

    private JsonDeserializer<Task> buildTaskDeserializer () {
        return (json, typeOfT, context) -> {
            JsonObject jsonObject = json.getAsJsonObject();
            try {
                String type = jsonObject.get("taskType").getAsString();
                switch (type) {
                case "TODO":
                    return new ToDo(jsonObject.get("isDone").getAsString(),
                                    jsonObject.get("description").getAsString());
                case "EVENT":
                    return new Event(jsonObject.get("isDone").getAsString(),
                                     jsonObject.get("description").getAsString(),
                                     jsonObject.get("from").getAsString(),
                                     jsonObject.get("to").getAsString());
                case "DEADLINE":
                    return new Deadline(jsonObject.get("isDone").getAsString(),
                                        jsonObject.get("description").getAsString(),
                                        jsonObject.get("by").getAsString());
                default:
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        };
    }

    public String getFilePath () {
        return filePath;
    }

    /**
     *
     * @return
     * @throws FileNotFoundException
     * @throws NoSuchFileException
     */
    public ArrayList<Task> readFile () throws FileNotFoundException, NoSuchFileException {
        try {
            Reader br = Files.newBufferedReader(Paths.get(getFilePath()));
            return gson.fromJson(br, new TypeToken<ArrayList<Task>>() {
            }.getType());

        } catch (IOException e) {
            System.out.println("Something went wrong while reading from the file");
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param tasks
     */
    public void writeFile (TaskList tasks) {
        File file = new File(getFilePath());
        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            FileWriter fw = new FileWriter(file);
            gson.toJson(tasks.getTasks(), fw);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    @Override
    public String toString () {
        return "Storage{" +
                "filePath='" + filePath + '\'' +
                '}';
    }
}
