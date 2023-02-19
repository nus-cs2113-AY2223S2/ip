import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Task.Task;
import Task.Deadline;
import Task.Todo;
import Task.Event;

public class DukeStorage {
    private static String filePath;

    public DukeStorage(String filePath) {
        DukeStorage.filePath = filePath;
    }

    public void saveTaskList(ArrayList<Task> tasks) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file);
            for (Task task : tasks) {
                writer.write(task.toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    public static ArrayList<Task> loadTaskList() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        Task.numberOfTasks = 0;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                Task task = new Task(null);
                if (line.startsWith("T")) {
                    task = Todo.fromFileString(line);
                } else if (line.startsWith("D")) {
                    task = Deadline.fromFileString(line);
                } else if (line.startsWith("E")) {
                    task = Event.fromFileString(line);
                }
                tasks.add(task);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
        return tasks;
    }
}
