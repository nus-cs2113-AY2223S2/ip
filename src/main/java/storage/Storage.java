package storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    protected String filePath;
    protected File f;
    protected ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructor for Storage/
     *
     * @param filePath where the file is saved in String
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Create txt file that stores task list if it has not been created.
     */
    public void createFile() {
        try {
            f = new File(filePath);
            if (f.createNewFile()) {
                System.out.println("File created: " + f.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating file.");
            e.printStackTrace();
        }
    }

    /**
     * Initialise tasks array list using tasks stored in the file. Retrieve history.
     *
     * @throws FileNotFoundException if no file can be found.
     */
    public void readTasksFromFile() throws FileNotFoundException {
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String task = s.nextLine();
            String[] taskElements = task.trim().split("\\|");
            String taskType = taskElements[0].trim();
            boolean taskStatus = taskElements[1].trim().equals("X");
            String taskDescription = taskElements[2].trim();
            switch (taskType) {
            case "T":
                tasks.add(new ToDo(taskDescription, taskStatus));
                break;
            case "D":
                String deadlineDate = taskElements[3].trim();
                tasks.add(new Deadline(taskDescription, taskStatus, deadlineDate));
                break;
            case "E":
                String startDate = taskElements[3].trim();
                String endDate = taskElements[4].trim();
                tasks.add(new Event(taskDescription, taskStatus, startDate, endDate));
                break;
            default:
            }
        }
    }

    /**
     * Save newly added tasks to file based on different task type.
     *
     * @throws IOException if saving failed.
     */
    public void saveTasksToFile() throws IOException {
        FileWriter fw = new FileWriter(filePath, false);
        for (Task t : tasks) {
            String completeTaskDescription = t.printTask();
            String taskType = completeTaskDescription.substring(1, 2); // task description starts with [TaskType]
            switch (taskType) {
            case "T":
                fw.write(taskType + " | " + t.getStatusIcon() + " | " + t.getDescription() +
                        System.lineSeparator());
                break;
            case "D":
                fw.write(taskType + " | " + t.getStatusIcon() + " | " + t.getDescription() + " | " +
                        ((Deadline) t).getDeadline() + System.lineSeparator());
                break;
            case "E":
                fw.write(taskType + " | " + t.getStatusIcon() + " | " + t.getDescription() + " | " +
                        ((Event) t).getStartTime() + " | " + ((Event) t).getEndTime() + System.lineSeparator());
                break;
            default:
            }
        }
        fw.close();
    }

    /**
     * Getter for the arraylist of task list initialised.
     *
     * @return tasks as ArrayList of Task.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
