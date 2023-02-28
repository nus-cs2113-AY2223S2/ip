package commands;

import constants.constant;
import exceptions.InvalidTaskException;
import tasks.Deadline;
import tasks.Task;

import java.util.ArrayList;

public class AddDeadline {

    /**
     * add deadline task into ArrayList
     * @param ins UserCommand which contains task description and deadline
     */
    public static void addDeadlineTask(ArrayList<Task> list, String ins) {
        try {

            if (!ins.contains("/by")) {
                throw new InvalidTaskException();
            }

            String[] arrOfStr = ins.split("deadline");
            arrOfStr = arrOfStr[1].split("/by ", 2);
            String description = arrOfStr[0];
            String by = arrOfStr[1];

            if (by.equals("")) {
                throw new InvalidTaskException();
            } else if (!by.contains("/")) {
                throw new InvalidTaskException();
            }
            Deadline newTask = new Deadline(ins, description, by);
            list.add(newTask);
            System.out.println("Added: " + ins);
            System.out.println(constant.HORIZONTAL_LINE + "\n");
        } catch (InvalidTaskException e) {
            System.out.println(e.call());
            System.out.println(constant.HORIZONTAL_LINE + "\n");
        }
    }
}
