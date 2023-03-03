package Storage;

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
    // filePath of the file
    private static String filePath;

    /**
     * Constructor
     *
     * @param filePath the path to the file
     */
    public DukeStorage(String filePath) {
        DukeStorage.filePath = filePath;
    }

    /**
     * This method save the task list.
     *
     * @param tasks the task list
     * 
     */
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

    /**
     * This method loads the task list.
     *
     * @return the task list
     * @throws IOException
     */
    public static ArrayList<Task> loadTaskList() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line.startsWith("T")) {
                    Task task = Todo.fromFileString(line);
                    tasks.add(task);
                } else if (line.startsWith("D")) {
                    Task task = Deadline.fromFileString(line);
                    tasks.add(task);
                } else if (line.startsWith("E")) {
                    Task task = Event.fromFileString(line);
                    tasks.add(task);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
        return tasks;
    }
}
