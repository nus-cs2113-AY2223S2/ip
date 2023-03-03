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
     * This function determines the task type of an existing line in the text file and calls the corresponding function
     * to format the task accordingly.
     * @param line
     * @param tasks
     * @param filePath
     */
    public static void copyToArrayList(String line, ArrayList<Task> tasks, String filePath) {
        switch (line.substring(0, 1)) {
        case "T":
            copyTodoToArrayList("T", line.substring(2), tasks);
            break;
        case "D":
            copyDeadlineToArrayList("D", line.substring(2), tasks);
            break;
        case "E":
            copyEventToArrayList("E", line.substring(2), tasks);
            break;
        case "S":
            // Saved data text on the first line (initialisation step)
            break;
        default:
            System.out.println("Unknown task type detected...");
            System.out.println("Skipping task...");
        }
    }

    /**
     * Formats task of type ToDo so that it can be added to ArrayList.
     * Checks and updates the task status accordingly based on whether it was marked as done in the text file.
     * @param taskType
     * @param taskInfo
     * @param tasks
     */
    private static void copyTodoToArrayList(String taskType, String taskInfo, ArrayList<Task> tasks) {
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
     * @param taskType
     * @param taskInfo
     * @param tasks
     */
    private static void copyDeadlineToArrayList(String taskType, String taskInfo, ArrayList<Task> tasks) {
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
     * @param taskType
     * @param taskInfo
     * @param tasks
     */
    private static void copyEventToArrayList(String taskType, String taskInfo, ArrayList<Task> tasks) {
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
