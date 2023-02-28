package commands;

import constants.constant;
import exceptions.InvalidTaskException;
import tasks.Task;

import java.util.ArrayList;

public class DeleteTask {

    /**
     * delete the task in the ArrayList
     * @param ins userCommand which contains the task number which should be deleted
     */
    public static void deleteTask(ArrayList<Task> list, String ins) {
        try {
            if (list.size() == 0) {
                System.out.println("Your day is clear! there is no task");
                System.out.println(constant.HORIZONTAL_LINE + "\n");
            } else {
                int idx = 7;
                String subStr = ins.substring(idx);
                if (subStr.contains(" ")) {
                    System.out.println("Please specify the task you want to mark :) ");
                } else if (subStr.equals("all")) {
                    list.clear();
                    System.out.println("All entries are deleted");
                    System.out.println(constant.HORIZONTAL_LINE + "\n");
                } else {
                    int taskNum = Integer.parseInt(subStr);
                    boolean isValid = taskNum > 0 && taskNum <= list.size();
                    if (!isValid) {
                        throw new InvalidTaskException();
                    }
                    list.remove(taskNum - 1);
                    System.out.println("Task " + taskNum + " deleted");
                    System.out.println(constant.HORIZONTAL_LINE + "\n");
                }
            }
        } catch (InvalidTaskException e) {
            System.out.println(e.call());
            System.out.println(constant.HORIZONTAL_LINE + "\n");
        }
    }
}
