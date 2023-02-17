package duke.storage;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

public class Storage {
    private static final String home = System.getProperty("user.dir");
    private static final Path SAVE_DIR = Paths.get(home, "data");
    private static final Path SAVE_FILE = Paths.get(SAVE_DIR.toString(), "save.txt");
    private static GsonBuilder builder = new GsonBuilder();
    private static Gson gson = builder.create();
    private static ArrayList<Task> savedTasks;

    private static void checkSaveDir() {
        // check if file/directory exists
        File dir = new File(SAVE_DIR.toUri());
        if (!dir.exists()) { // create directory if required
            dir.mkdir();
        }
    }

    private static String serializeJSON(Task t) {
        return gson.toJson(t);
    }

    private static Task deserializeJSON(String json) {
        JsonObject j = gson.fromJson(json, JsonObject.class);
        Task t;
        String taskType = j.get("type").getAsString();
        switch (taskType) {
        case "TODO":
            t = gson.fromJson(j, ToDo.class);
            break;
        case "EVENT":
            t = gson.fromJson(j, Event.class);
            break;
        case "DEADLINE":
            t = gson.fromJson(j, Deadline.class);
            break;
        default:
            t = gson.fromJson(j, Task.class);
        }
        return t;
    }

    public static ArrayList<Task> getTasks() {
        File saveData = new File(SAVE_FILE.toUri());
        savedTasks = readTasks(saveData);
        return savedTasks;
    }

    private static ArrayList<Task> readTasks(File saveData) {
        checkSaveDir();
        ArrayList<Task> fileTasks = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(saveData);
            while (scanner.hasNextLine()) {
                String fileJSON = scanner.nextLine();
                fileTasks.add(deserializeJSON(fileJSON));
            }
        } catch (FileNotFoundException err) {
            System.out.println(err.getMessage());
        }
        return fileTasks;
    }

    // https://nus-cs2113-ay2223s2.github.io/website/schedule/week6/topics.html#w6-3-java-file-access
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        checkSaveDir();
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void writeTasks(ArrayList<Task> tasks) throws IOException {
        StringBuilder saveData = new StringBuilder();
        for (Task t : tasks) {
            String json = serializeJSON(t);
            saveData.append(json);
            saveData.append(System.lineSeparator());
        }
        writeToFile(SAVE_FILE.toString(), saveData.toString());
    }
}
