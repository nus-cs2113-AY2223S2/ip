package command;

import utility.Ui;

import java.util.ArrayList;
import tasks.Task;

public class unmark {
    public static void executeUnmark (String userInput, ArrayList<Task> tasks) {
        int taskIndex = Integer.parseInt(userInput.split(" ", 2)[1]);
        (tasks.get(taskIndex - 1)).setIncomplete();

        // Prints acknowledgement
        Ui.markTask(tasks.get(taskIndex - 1).getTask(), "incomplete");
    }
}
