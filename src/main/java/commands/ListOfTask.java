package commands;

import constants.constant;
import tasks.Task;

public class ListOfTask extends Command {
    public static void printListOfTasks(Task[] list) {
        System.out.println(constant.HORIZONTAL_LINE + "\n");
        for (int i = 0; i < Task.getNum(); i++) {
            System.out.println(list[i].toString());
        }
        System.out.println("\n" + "Found " + (Task.getNum()) + " Task!");
        System.out.println(constant.HORIZONTAL_LINE + "\n");
    }
}
