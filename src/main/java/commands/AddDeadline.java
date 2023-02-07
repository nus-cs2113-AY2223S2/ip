package commands;

import constants.constant;
import exceptions.InvalidTaskException;
import tasks.Deadline;
import tasks.Task;

public class AddDeadline extends Command {

    public static void addDeadlineTask(Task[] list, String ins) {
        try {
            System.out.println(constant.HORIZONTAL_LINE + "\n");

            if (!ins.contains("/by")) {
                throw new InvalidTaskException();
            }

            String[] arrOfStr = ins.split("deadline");
            arrOfStr = arrOfStr[1].split("/by ", 2);
            String description = arrOfStr[0];
            String by = arrOfStr[1];

            if (by.equals("")) {
                throw new InvalidTaskException();
            }

            list[Task.getNum()] = new Deadline(description, by);
            System.out.println("Added: " + ins);
            System.out.println(constant.HORIZONTAL_LINE + "\n");
        } catch (InvalidTaskException e) {
            System.out.println(e.call());
            System.out.println(constant.HORIZONTAL_LINE + "\n");
        }
    }
}
