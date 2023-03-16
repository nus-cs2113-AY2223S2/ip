package command;

import utility.Ui;

import tasks.Task;
import java.util.ArrayList;
public class list {
    public static void printTasks (ArrayList<Task> tasks) {
        Ui.printTasks(tasks);
    }
}
