package command;

import java.util.ArrayList;

import tasks.Task;
import utility.Ui;

public class delete {
    public static void executeDelete(String userInput, ArrayList<Task> tasks) {
        // Sets specified task to incomplete
        int taskIndex = Integer.parseInt(userInput.split(" ", 2)[1]) - 1;

        // Prints acknowledgement
        Ui.deleteTask((tasks.get(taskIndex - 1)).getTask());

        // Deletes task
        tasks.remove(taskIndex);
    }
}
