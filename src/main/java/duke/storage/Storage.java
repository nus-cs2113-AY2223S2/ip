package duke.storage;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class saves, deletes, and updates tasks in a file.
 */
public class Storage {

    private String filePath;
    private TaskList tasks;

    /**
     * Initializes filePath and tasks.
     *
     * @param filePath Location of file containing saved tasks.
     * @param tasks List of all tasks.
     */
    public Storage(String filePath, TaskList tasks) {
        this.filePath = filePath;
        this.tasks = tasks;
    }

    /**
     * Create file that will store tasks.
     */
    public void createSavedTasksFile() {
        File savedTasks = new File(filePath);
        // check if file already exists
        if (!savedTasks.exists()) {
            try {
                savedTasks.createNewFile();
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        }
    }

    /**
     * Loads the tasks saved in file into tasks list.
     *
     * @throws FileNotFoundException If file does not exist.
     */
    public void loadSavedTasks() throws FileNotFoundException {
        File savedTasks = new File(filePath);
        if (savedTasks.exists()) {
            Scanner scanner = new Scanner(savedTasks);
            while (scanner.hasNext()) {
                String task = scanner.nextLine();
                String[] splitTask = task.split(" \\| ");
                addSavedTasksToList(splitTask);
            }
        }
    }

    private void addSavedTasksToList(String[] splitTask) {
        String taskType = splitTask[0];
        if (taskType.equals("T") || taskType.equals("D") || taskType.equals("E")) {
            boolean isDone = Boolean.parseBoolean(splitTask[1]);
            String taskDescription = splitTask[2];
            if (taskType.equals("T")) {
                tasks.addTodo(taskDescription, isDone);
            } else if (taskType.equals("D") || taskType.equals("E")) {
                String date = splitTask[3];
                if (taskType.equals("D")) {
                    tasks.addDeadline(taskDescription + " /by " + date, isDone);
                } else {
                    tasks.addEvent(taskDescription + " " + date, isDone);
                }
            }
        }
    }

    /**
     * Saves a task in the tasks list to the file.
     *
     * @throws IOException If unable to write to the file.
     */
    public void saveTaskToFile() throws IOException {
        FileWriter writer = new FileWriter(filePath);
        writer.write(formatTask());
        writer.close();
    }

    private String formatTask() {
        String formattedTask = "";
        for (int i = 1; i <= tasks.getNumTasks(); i++) {
            Task task = tasks.getTasks().get(i);
            String taskType = task.getTaskType();
            String taskStatus = task.getStatusIcon();
            String taskDescription = task.getDescription();
            if (taskStatus.equals("X")) {
                taskStatus = "true";
            } else {
                taskStatus = "false";
            }
            if (taskType.equals("T")) {
                formattedTask += taskType + " | " + taskStatus + " | " + taskDescription + System.lineSeparator();
            } else if (taskType.equals("D")){
                Deadline deadline = (Deadline) task;
                formattedTask += taskType + " | " + taskStatus + " | " + taskDescription + " | " +
                        deadline.getDeadline() + System.lineSeparator();
            } else {
                Event event = (Event) task;
                formattedTask += taskType + " | " + taskStatus + " | " + taskDescription + " | from: " +
                        event.getFromDate() + " to: " + event.getToDate() + System.lineSeparator();
            }
        }
        return formattedTask;
    }

}