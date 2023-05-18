package tusky.storage;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import tusky.tasks.*;
import tusky.ui.Ui;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;


/**
 * The Storage class handles the reading from and writing to the file.
 * It uses the Gson library to serialize and deserialize JSON data.
 */
public class Storage {
    private final String filePath;
    private final Gson gson;
    private final Ui ui;

    /**
     * Instantiates a new Storage with the file path provided.
     * It uses the GsonBuilder class to create a custom Gson object to parse
     * our custom Task class and dates.
     * @param filePath Path to the file to be read from and written to.
     * @param ui Ui object to be used for interacting with the user
     */
    public Storage (String filePath, Ui ui) {

        this.filePath = filePath;

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Task.class, new TaskAdapter(ui));
        gsonBuilder.registerTypeAdapter(LocalDate.class, new DateAdapter());
        this.gson = gsonBuilder.create();
        this.ui = ui;
    }


    public String getFilePath () {
        return filePath;
    }

    /**
     * Reads the file and returns an ArrayList of Tasks.
     * @return ArrayList of Tasks
     * @throws IOException If the file is not found or cannot be read.
     */
    public ArrayList<Task> readFile () throws IOException {
        Reader br = Files.newBufferedReader(Paths.get(getFilePath()));
        return gson.fromJson(br, new TypeToken<ArrayList<Task>>() {}.getType());
    }

    /**
     * Writes the TaskList to the file.
     * It also creates the file and/or parent directories if they do not exist.
     * @param tasks TaskList to be written to the file.
     */
    public void writeFile (TaskList tasks) {
        File file = new File(getFilePath());
        FileWriter fw;
        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            fw = new FileWriter(file);
            gson.toJson(tasks.getTasks(), fw);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            ui.showUnknownException(e);
        }
    }

    @Override
    public String toString () {
        return "Storage{" +
                "filePath='" + filePath + '\'' +
                '}';
    }
}
