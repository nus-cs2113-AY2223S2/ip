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

    public void saveTasks(TaskList tasks) {
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
            ex.printStackTrace();
        }
    }

    public TaskList loadTasks() {
        // Check for file existence
        if (!Files.isRegularFile(filePath)) {
            return new TaskList();
        }

        // Read from file and deserialize
        try (FileInputStream fileInputStream = new FileInputStream(filePath.toString());
                ObjectInputStream inputStream = new ObjectInputStream(fileInputStream)) {
            return (TaskList) inputStream.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Something went wrong! I couldn't read your tasks :(");
            ex.printStackTrace();
        }

        return new TaskList();
    }

}
