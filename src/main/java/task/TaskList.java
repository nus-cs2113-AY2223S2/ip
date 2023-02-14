package task;

import java.util.Arrays;
import io.IO;

public class TaskList {
    private static final Task[] tasks = new Task[100];
    private static int numberOfTasks = 0;

    /**
     * Print the contents of Task List
     */
    public static String getTaskListString() {
        String output = "Your Tasks: \n";
        for (Task task : Arrays.copyOf(tasks, numberOfTasks)) {
            output += task.getTaskNumber() + task.toString() + '\n';
        }
        return output;
    }

    /**
     * Adds a String task to the list of strings.
     * @param task: Name of task, String to be added
     */
    public void addTask(Task task) {
        tasks[numberOfTasks] = task;
        numberOfTasks++;
        IO.writeToFile(task.getFileWriteFormat());
    }

    /**
     * Two-in-one method to mark or unmark task since it is so similar
     * @param command Either "mark" or "unmark"
     * @param commandArgs Should be an int corresponding to the task number (1-index)
     * @return Feedback string: "Task marked: ____"
     */
    public static String executeMarkUnmark(String command, String commandArgs) {
        int taskNumber;
        // Check is Integer
        try {
            taskNumber = Integer.parseInt(commandArgs);
        } catch (NumberFormatException e) {
            // Not an int. Send back an error message.
            return IO.ERROR_MESSAGE_TASK_INDEX;
        }
        // Check Integer is in range
        try {
            if (command.equals("mark")) {
                return tasks[taskNumber - 1].markAsDone();
            } else if (command.equals("unmark")) {
                return tasks[taskNumber - 1].markAsUndone();
            }
        } catch (IndexOutOfBoundsException e) {
            return IO.ERROR_MESSAGE_TASK_INDEX;
        }
        // If nothing else works...
        return "Huh?";
    }

    // Getter for number of task
    public static int getNumberOfTasks() {
        return numberOfTasks;
    }

    // A bit unoptimised, but this is to get the next number for numbering purposes.
    public static int getNextTaskNumber() {
        return numberOfTasks + 1;
    }
}
