package command;

import utility.Ui;

import java.util.ArrayList;

import tasks.Task;

public class mark {
    /**
     * Marks tasks, setting them as complete
     * @param userInput Index of task
     * @param tasks Array list of tasks
     */
    public static void executeMark(String userInput, ArrayList<Task> tasks) {
        int taskIndex = Integer.parseInt(userInput.split(" ", 2)[1]);
        (tasks.get(taskIndex - 1)).setComplete();

        // Prints acknowledgement
        Ui.markTask(tasks.get(taskIndex - 1).getTask(), "complete");

    }
}
