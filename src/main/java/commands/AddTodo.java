package commands;

import constants.constant;
import exceptions.InvalidTaskException;
import tasks.Task;
import tasks.Todo;

import java.util.ArrayList;

public class AddTodo {
    /**
     * add event task into ArrayList
     * @param ins UserCommand which contains task description
     */
    public static void addTodoTask(ArrayList<Task> list, String ins) {
        try {
            if (!ins.contains(" ")) {
                throw new InvalidTaskException();
            }
            String[] arrOfStr = ins.split("todo", 2);
            String description = arrOfStr[1];
            Todo newTask = new Todo(ins, description);
            list.add(newTask);
            System.out.println("Added: " + ins);
            System.out.println(constant.HORIZONTAL_LINE + "\n");
        } catch (InvalidTaskException e) {
            System.out.println(e.call());
            System.out.println(constant.HORIZONTAL_LINE + "\n");
        }
    }
}
