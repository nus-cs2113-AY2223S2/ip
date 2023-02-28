package duke.command;

import duke.Duke;
import duke.Parser;
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
        String description = Parser.extractInfo(userCommand, "desc", "todo");
        storedUserTasks.add(new Todo(description));
        Display.displayCreateTask(storedUserTasks);
        Duke.userTextCount++;
        Display.printNumberOfTasks();
    }

    public static void createDeadline(String userCommand, ArrayList<Task> storedUserTasks) throws DukeException {
        if (userCommand.length()<=8) {
            throw new BlankDescException();
        }
        String description = Parser.extractInfo(userCommand, "desc", "deadline");
        String by = Parser.extractInfo(userCommand, "by", "none");
        storedUserTasks.add(new Deadline(description,by));
        Display.displayCreateTask(storedUserTasks);
        Duke.userTextCount++;
        Display.printNumberOfTasks();
    }

    public static void createEvent(String userCommand, ArrayList<Task> storedUserTasks) throws DukeException {
        if (userCommand.length()<=5) {
            throw new BlankDescException();
        }
        String description = Parser.extractInfo(userCommand, "desc", "event");
        String from = Parser.extractInfo(userCommand, "from", "none");
        String to = Parser.extractInfo(userCommand, "to", "none");
        storedUserTasks.add(new Event(description,from,to));
        Display.displayCreateTask(storedUserTasks);
        Duke.userTextCount++;
        Display.printNumberOfTasks();
    }
}
