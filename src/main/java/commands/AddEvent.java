package commands;

import constants.constant;
import exceptions.InvalidTaskException;
import tasks.Event;
import tasks.Task;

public class AddEvent extends Command {
    public static void addEventTask(Task[] list, int counter, String ins) throws InvalidTaskException {
        try {
            String[] arrOfStr = ins.split("event", 2);
            if(!ins.contains("/from") || !ins.contains("/to")){
                throw new InvalidTaskException();
            }
            arrOfStr = arrOfStr[1].split("/", 3);
            String description = arrOfStr[0];
            String from = arrOfStr[1].split("from")[1];
            String to = arrOfStr[2].split("to")[1];
            if(from.isEmpty() || to.isEmpty()){
                throw new InvalidTaskException();
            }
            list[Task.getNum()] = new Event(description, from, to);
            System.out.println("Added: " + ins);
            System.out.println(constant.HORIZONTAL_LINE + "\n");
        }catch (InvalidTaskException e){
            System.out.println(e.call());
            System.out.println(constant.HORIZONTAL_LINE + "\n");
        }
    }
}
