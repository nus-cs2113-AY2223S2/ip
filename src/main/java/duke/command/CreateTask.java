package duke.command;

import duke.Duke;
import duke.exception.BlankDescException;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

public class CreateTask {
    public static void createTodo(String userCommand, ArrayList<Task> storedUserTasks) throws DukeException {
        if (userCommand.length()<=4) {
            throw new BlankDescException();
        }
        String description = userCommand.substring(5);
        storedUserTasks.add(new Todo(description));
        Display.displayCreateTask(storedUserTasks);
        Duke.userTextCount++;
        Display.printNumberOfTasks();
    }

    public static void createDeadline(String userCommand, ArrayList<Task> storedUserTasks) throws DukeException {
        if (userCommand.length()<=8) {
            throw new BlankDescException();
        }
        int indexOfBy = userCommand.indexOf("/by");
        String description = userCommand.substring( 9, indexOfBy-1);
        String by = userCommand.substring(indexOfBy+4);
        storedUserTasks.add(new Deadline(description,by));
        Display.displayCreateTask(storedUserTasks);
        Duke.userTextCount++;
        Display.printNumberOfTasks();
    }

    public static void createEvent(String userCommand, ArrayList<Task> storedUserTasks) throws DukeException {
        if (userCommand.length()<=5) {
            throw new BlankDescException();
        }
        int indexOfFrom = userCommand.indexOf("/from");
        int indexOfTo = userCommand.indexOf("/to");
        String description = userCommand.substring(6,indexOfFrom-1);
        String from = userCommand.substring(indexOfFrom+6,indexOfTo-1);
        String to = userCommand.substring(indexOfTo+4);
        storedUserTasks.add(new Event(description,from,to));
        Display.displayCreateTask(storedUserTasks);
        Duke.userTextCount++;
        Display.printNumberOfTasks();
    }
}
