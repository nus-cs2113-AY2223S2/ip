package tusky.storage;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import tusky.tasks.*;
import tusky.ui.Ui;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;


public class Storage {
    private final String filePath;

    private final Gson gson;
    private final Ui ui;

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
     * @return
     * @throws FileNotFoundException
     * @throws NoSuchFileException
     */
    public ArrayList<Task> readFile () throws FileNotFoundException, NoSuchFileException, IOException {
            Reader br = Files.newBufferedReader(Paths.get(getFilePath()));
            return gson.fromJson(br, new TypeToken<ArrayList<Task>>() {
            }.getType());
    }

    /**
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
