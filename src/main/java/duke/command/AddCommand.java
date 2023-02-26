package duke.command;

import duke.exceptions.EmptyTaskException;
import duke.tasklist.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.util.ArrayList;

import static duke.ui.Ui.printBorder;

public class AddCommand {
    //add a new to do
    public static void addTodo(TaskList tasks, String name) throws EmptyTaskException {
        if(name.equals(" ")){
            throw new EmptyTaskException();
        }
        tasks.addTask(new Todo(name, false));
        printBorder();
        System.out.println("added: " + name + "\n");
        printBorder();
    }

    //add a new deadline task
    public static void addDeadline(TaskList tasks, String name, String deadline){
        tasks.addTask(new Deadline(name, deadline, false));
        printBorder();
        System.out.println("added: " + name + "\n");
        printBorder();
    }

    //add a new event
    public static void addEvent(TaskList tasks, String name, String start, String end){
        tasks.addTask(new Event(name, start, end, false));
        printBorder();
        System.out.println("added: " + name + "\n");
        printBorder();
    }
}
