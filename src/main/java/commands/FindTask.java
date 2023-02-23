package commands;

import constants.constant;
import exceptions.InvalidTaskException;
import tasks.Task;

import java.util.ArrayList;

public class FindTask {
    public static void findTask(ArrayList<Task> list, String ins) {
        try {
            if (!ins.contains(" ")) {
                throw new InvalidTaskException();
            }
            int index = 1;
            ArrayList<Task> arrOfMatchingTasks = new ArrayList<>();
            String keyword = ins.split("find ", 2)[1];
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getDescription().contains(keyword)) {
                    arrOfMatchingTasks.add(list.get(i));
                }
            }
            if (arrOfMatchingTasks.size() == 0) {
                System.out.println("There is no matching Task");
            } else {
                System.out.println("\n" + "Found " + (arrOfMatchingTasks.size()) + " Task!");
                System.out.println("Here are the matching tasks in your list:");
                for (Task task : arrOfMatchingTasks) {
                    System.out.println(index + task.toString());
                    index++;
                }
            }
            System.out.println(constant.HORIZONTAL_LINE + "\n");
        } catch (InvalidTaskException e) {
            System.out.println(e.call());
        }

    }
}
