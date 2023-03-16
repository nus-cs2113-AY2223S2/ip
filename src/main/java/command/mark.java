package command;

import utility.Ui;

import java.util.ArrayList;
import tasks.Task;

public class mark {
    public static void executeMark (String userInput, ArrayList<Task> tasks) {
        int taskIndex = Integer.parseInt(userInput.split(" ", 2)[1]);
        (tasks.get(taskIndex - 1)).setComplete();

        // Prints acknowledgement
        Ui.markTask(tasks.get(taskIndex - 1).getTask(), "complete");

    }
}
