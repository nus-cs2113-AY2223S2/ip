package command;

import utility.Ui;

import tasks.Task;

import java.util.ArrayList;

public class list {
    /**
     * Lists all tasks, including type and completion status
     * @param tasks Array list of tasks
     */
    public static void printTasks(ArrayList<Task> tasks) {
        Ui.printTasks(tasks);
    }
}
