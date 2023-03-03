package duke.storage;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.util.ArrayList;

/**
 * This class reads all the tasks stored in the text file and copies it over to the ArrayList.
 * This is so that the user can easily manipulate the existing tasks or create new ones.
 */
public class ReadFromFile {

    /**
     * This function determines the whether the task from a line in the text file is a ToDo, Deadline or Event and
     * calls the corresponding function to format the task accordingly.
     * Case S is added to ignore the first line of the text file as it contains "Saved tasks" as its first line
     * during the initialisation phase.
     *
     * @param line  String from text file
     * @param tasks ArrayList of tasks to store the task objects
     */
    public void copyToArrayList(String line, ArrayList<Task> tasks) {
        switch (line.substring(0, 1)) {
        case "T":
            copyTodoToArrayList(line.substring(2), tasks);
            break;
        case "D":
            copyDeadlineToArrayList(line.substring(2), tasks);
            break;
        case "E":
            copyEventToArrayList(line.substring(2), tasks);
            break;
        case "S":
            // First line is: "Saved data" text
            // To ignore the first line (initialisation step)
            break;
        default:
            System.out.println("Unknown task type detected...");
            System.out.println("Skipping task...");
        }
    }

    /**
     * Formats task of type ToDo so that it can be added to ArrayList.
     * Checks and updates the task status accordingly based on whether it was marked as done in the text file.
     *
     * @param taskInfo Task description
     * @param tasks    ArrayList that stores task objects
     */
    private void copyTodoToArrayList(String taskInfo, ArrayList<Task> tasks) {
        // Parse line to split task status and task info
        String[] messageComponents = taskInfo.split("/", 2);
        // Create new todo with task info
        ToDo newToDo = new ToDo(messageComponents[1]);
        // Store todo in list of tasks
        tasks.add(newToDo);
        // Convert to 0 based indexing
        int currentTaskIndex = tasks.size() - 1;
        // Mark task as done if task status was stored as 1
        if (messageComponents[0].equals("1")) {
            Task currentTask = tasks.get(currentTaskIndex);
            currentTask.copyCompletedTask();
        }
    }

    /**
     * Formats tasks of type deadline so that it can be stored in ArrayList.
     * Checks and updates the task status accordingly based on whether it was marked as done in the text file.
     *
     * @param taskInfo Task description
     * @param tasks    ArrayList that stores task objects
     */
    private void copyDeadlineToArrayList(String taskInfo, ArrayList<Task> tasks) {
        String[] messageComponents = taskInfo.split("/", 3);
        Deadline newDeadline = new Deadline(messageComponents[1], messageComponents[2]);
        tasks.add(newDeadline);
        // Convert to 0 based indexing
        int currentTaskIndex = tasks.size() - 1;
        if (messageComponents[0].equals("1")) {
            Task currentTask = tasks.get(currentTaskIndex);
            currentTask.copyCompletedTask();
        }
    }

    /**
     * Formats tasks of type event so that it can be stored in ArrayList.
     * Checks and updates the task status accordingly based on whether it was marked as done in the text file.
     *
     * @param taskInfo Task description
     * @param tasks    ArrayList that stores task objects
     */
    private void copyEventToArrayList(String taskInfo, ArrayList<Task> tasks) {
        String[] messageComponents = taskInfo.split("/", 4);
        Event newEvent = new Event(messageComponents[1], messageComponents[2], messageComponents[3]);
        tasks.add(newEvent);
        // Convert to 0 based indexing
        int currentTaskIndex = tasks.size() - 1;
        if (messageComponents[0].equals("1")) {
            Task currentTask = tasks.get(currentTaskIndex);
            currentTask.copyCompletedTask();
        }
    }

}
