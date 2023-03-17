package command;

import java.util.ArrayList;

import tasks.Task;
import utility.Ui;

public class delete {
    /**
     * Deletes task
     * @param userInput Index to be deleted
     * @param tasks List of tasks
     */
    public static void executeDelete(String userInput, ArrayList<Task> tasks) {
        // Sets specified task to incomplete
        int taskIndex = Integer.parseInt(userInput.split(" ", 2)[1]) - 1;

        // Prints acknowledgement
        Ui.deleteTask((tasks.get(taskIndex - 1)).getTask());

        // Deletes task
        tasks.remove(taskIndex);
    }
}
