package duke.storage;
import duke.task.Task;
import duke.utils.Ui;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class DataManager {
    protected final String filePath;
    protected final ArrayList<Task> tasks;

    public DataManager(String filePath, ArrayList<Task> tasks) {
        this.filePath = filePath;
        this.tasks = tasks;
    }

    public void writeToFile() throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        for (Task task : this.tasks) {
            fw.write(task.toString() + System.lineSeparator());
        }
        fw.close();
    }
    public void createFile() throws IOException {
        File file = new File(this.filePath);
        Path path = Paths.get(filePath);
        Files.createDirectories(path.getParent());
        file.createNewFile();
    }

    public void writeToFileWithErrorHandler() throws IOException {
        try {
            writeToFile();
        } catch (FileNotFoundException e) {
            System.out.println("Meow! Something went wrong: " + e.getMessage()
                    + System.lineSeparator() + Ui.LINE);
            try {
                createFile();
            } catch (IOException error){
                System.out.println("Meow! Failed to create a file: " + e.getMessage()
                        + System.lineSeparator() + Ui.LINE);
            }
            System.out.println("File Created!");
        }
        writeToFile();
    }
}

