package storage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import task.Task;

public class TaskStorageManager {

    private static final Path PATH_SEP = Path.of(FileSystems.getDefault().getSeparator());
    // Stores to: TEMP/ip/task-storage
    public static final Path DEFAULT_FILE_PATH =
        Path.of(System.getProperty("java.io.tmpdir") + PATH_SEP + "ip" + PATH_SEP + "task-storage.dat");

    private final Path filePath;

    public TaskStorageManager(Path filePath) {
        this.filePath = filePath;
    }

    public TaskStorageManager() {
        this.filePath = DEFAULT_FILE_PATH;
    }

    public void saveTasks(List<Task> tasks) {
        // Check for file existence
        if (!Files.isRegularFile(filePath)) {
            try {
                Files.createDirectories(filePath.getParent());
                Files.createFile(filePath);
            } catch (IOException ex) {
                System.out.println("Couldn't save your files to: " + filePath + "!");
                ex.printStackTrace();
                return;
            }
        }

        // Serialize and write to file
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath.toString());
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream)) {
            outputStream.writeObject(tasks);
        } catch (IOException ex) {
            System.out.println("Something went wrong! I couldn't save your tasks :(");
        }
    }

    @SuppressWarnings("unchecked")
    public List<Task> loadTasks() {
        // Check for file existence
        if (!Files.isRegularFile(filePath)) {
            return new ArrayList<>();
        }

        // Read from file and deserialize
        try (FileInputStream fileInputStream = new FileInputStream(filePath.toString());
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream)) {
            return (ArrayList<Task>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Something went wrong! I couldn't read your tasks :(");
        }

        return new ArrayList<>();
    }

}
