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

/**
 * Saves the updated task list to the file in designated file path.
 */
public class DataManager {
    protected final String filePath;
    protected final ArrayList<Task> tasks;
    public DataManager(String filePath, ArrayList<Task> tasks) {
        this.filePath = filePath;
        this.tasks = tasks;
    }

    /**
     * Writes to the existing local file according to the file path in this class.
     *
     * @throws IOException If the file is not found or cannot be accessed.
     */
    public void writeToFile() throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        for (Task task : this.tasks) {
            fw.write(task.toString() + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Creates a file in the designated file path in the class.
     *
     * @throws IOException If the file cannot be created.
     */
    public void createFile() throws IOException {
        File file = new File(this.filePath);
        Path path = Paths.get(filePath);
        Files.createDirectories(path.getParent());
        file.createNewFile();
    }

    /**
     * Updates the file with new task list according to the file path in this class.
     * If the file is not found, creates a file in the designated file path in the class and then writes to the file.
     *
     * @throws IOException If the file cannot be created.
     */
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

