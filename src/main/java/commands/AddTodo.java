package commands;

import constants.constant;
import exceptions.InvalidTaskException;
import tasks.Task;
import tasks.Todo;

public class AddTodo extends Command {

    public static void addTodoTask(Task[] list, int counter, String ins) throws InvalidTaskException {
        try {
            System.out.println(constant.HORIZONTAL_LINE + "\n");
            if (!ins.contains(" ")){
                throw new InvalidTaskException();
            }
            String[] arrOfStr = ins.split("todo", 2);
            String description = arrOfStr[1];
            list[Task.getNum()] = new Todo(description);
            System.out.println("Added: " + ins);
            System.out.println(constant.HORIZONTAL_LINE + "\n");
        }catch (InvalidTaskException e){
            System.out.println(e.call());
            System.out.println(constant.HORIZONTAL_LINE + "\n");
        }
    }
}
