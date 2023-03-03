package duke;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to handle storage
 */
public class Storage {
    private final String filePath;

    private final String divider = " / ";

    /**
     * Constructor for a new storage object
     * @param filePath location for the storage file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads the storage file and loads the data into a task list
     * @return an ArrayList of the loaded tasks
     * @throws Exception error encountered when loading data
     */
    public ArrayList<Task> load() throws Exception {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new java.io.File(filePath);
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                tasks.add(string2Task(data));
            }
        } catch (FileNotFoundException fe) {
            File file = new java.io.File(filePath);
            try {
                file.createNewFile();
            } catch (IOException ioe) {
                throw new Exception("Error creating new data file. " + ioe);
            }
        }

        return tasks;
    }

    /**
     * Saves the tasks in memory to the data file
     * @param taskList the TaskList object
     * @throws Exception error occurred when saving
     */
    public void save(TaskList taskList) throws Exception {
        StringBuilder data = new StringBuilder();
        for (Task task : taskList.getAllTasks()) {
            data.append(task2String(task));
        }

        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(data.toString());
            writer.close();
        } catch (IOException e) {
            throw new Exception("Error saving data to file.");
        }
    }

    private String task2String(Task task) {
        String result = "";
        switch (task.type) {
            case TODO:
                Todo todo = (Todo) task;
                result = result.concat("TODO" + divider);
                result = result.concat((todo.isDone ? "YES" : "NO") + divider);
                result = result.concat(todo.description + "\n");
                break;
            case DEADLINE:
                Deadline deadline = (Deadline) task;
                result = result.concat("DEADLINE" + divider);
                result = result.concat((deadline.isDone ? "YES" : "NO") + divider);
                result = result.concat(deadline.description + divider);
                result = result.concat(deadline.by + "\n");
                break;
            case EVENT:
                Event event = (Event) task;
                result = result.concat("EVENT" + divider);
                result = result.concat((event.isDone ? "YES" : "NO") + divider);
                result = result.concat(event.description + divider);
                result = result.concat(event.from + divider);
                result = result.concat(event.by + "\n");
                break;
        }
        return result;
    }

    private Task string2Task(String string) throws Exception {
        String[] parts = string.split(divider);

        boolean isDone = parts[1].equals("YES");
        String description = parts[2];

        Task task;

        switch (parts[0]) {
            case "TODO":
                task = new Todo(description);
                task.setIsDone(isDone);
                break;
            case "DEADLINE":
                String by = parts[3];
                task = new Deadline(description, by);
                task.setIsDone(isDone);
                break;
            case "EVENT":
                String from = parts[3];
                String to = parts[4];
                task = new Event(description, from, to);
                task.setIsDone(isDone);
                break;
            default:
                throw new Exception("Data file corrupted.");
        }

        return task;
    }
}