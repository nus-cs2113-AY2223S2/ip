package commands;

import constants.constant;
import exceptions.InvalidTaskException;
import tasks.Event;
import tasks.Task;

import java.util.ArrayList;

public class AddEvent {
    /**
     * add event task into ArrayList
     * @param ins UserCommand contains task description and start and end dates
     */
    public static void addEventTask(ArrayList<Task> list, String ins) {
        try {
            String[] arrOfStr = ins.split("event", 2);
            if (!ins.contains("/from") || !ins.contains("/to")) {
                throw new InvalidTaskException();
            }
            arrOfStr = arrOfStr[1].split("/", 3);
            String description = arrOfStr[0];
            String from = arrOfStr[1].split("from")[1];
            String to = arrOfStr[2].split("to")[1];
            if (from.isEmpty() || to.isEmpty()) {
                throw new InvalidTaskException();
            }
            Event newTask = new Event(ins, description, from, to);
            list.add(newTask);
            System.out.println("Added: " + ins);
            System.out.println(constant.HORIZONTAL_LINE + "\n");
        } catch (InvalidTaskException e) {
            System.out.println(e.call());
            System.out.println(constant.HORIZONTAL_LINE + "\n");
        }
    }
}
