package storage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import task.TaskList;

public class TaskStorageManager {

    private final Path filePath;

    public TaskStorageManager(Path filePath) {
        this.filePath = filePath;
    }

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
