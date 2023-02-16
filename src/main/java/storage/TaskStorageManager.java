package storage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import task.TaskList;

/**
 * Handles the persistent saving and loading of a {@link TaskList}
 */
public class TaskStorageManager {

    private final Path filePath;

    /**
     * Constructor
     *
     * @param filePath Path to file for storing to / loading from
     */
    public TaskStorageManager(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves tasks to file
     *
     * @param tasks {@link TaskList} to save to. If file is not found / writable, will fail and throw an exception
     * @throws IOException If parent directories / file cannot be created or written to
     */
    public void saveTasks(TaskList tasks) throws IOException {
        // Check for file existence
        if (!Files.isRegularFile(filePath)) {
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
        }

        // Serialize and write to file
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath.toString());
                ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream)) {
            outputStream.writeObject(tasks);
        }
    }

    /**
     * Loads tasks from file
     *
     * @return Loaded tasks; will be empty if no previously saved tasks exist
     * @throws IOException            If file cannot be read from
     * @throws ClassNotFoundException If the saved {@link TaskList} is not compatible with the current class definition
     */
    public TaskList loadTasks() throws IOException, ClassNotFoundException {
        // Check for file existence
        if (!Files.isRegularFile(filePath)) {
            return new TaskList();
        }

        // Read from file and deserialize
        try (FileInputStream fileInputStream = new FileInputStream(filePath.toString());
                ObjectInputStream inputStream = new ObjectInputStream(fileInputStream)) {
            return (TaskList) inputStream.readObject();
        }
    }

}
