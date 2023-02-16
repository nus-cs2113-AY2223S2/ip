package commands;

import constants.constant;
import tasks.Task;

import java.util.ArrayList;

public class ListOfTask {
    public static void printListOfTasks(ArrayList<Task> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + list.get(i).toString());
        }
        System.out.println("\n" + "Found " + (list.size()) + " Task!");
        System.out.println(constant.HORIZONTAL_LINE + "\n");
    }
}
