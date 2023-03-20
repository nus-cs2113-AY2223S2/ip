package duke;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

public class Parser {
    public Command parse(String command) throws DukeException {

        if (command.startsWith("todo ")) {
            Task todoCommand = parseTodo(command);
            return new AddCommand(todoCommand);
        }
        else if (command.startsWith("deadline ")) {
            Task deadlineCommand = parseDeadline(command);
            return new AddCommand(deadlineCommand);
        }
        else if (command.startsWith("event ")) {
            Task eventCommand = parseEvent(command);
            return new AddCommand(eventCommand);
        }
        else if (command.startsWith("mark ")) {
            int index = Integer.parseInt(command.split("mark ")[1]);
            return new SetAsDoneCommand(index);
        }
        else if (command.startsWith("unmark ")) {
            int index = Integer.parseInt(command.split("unmark ")[1]);
            return new SetAsDoneCommand(index);
        }
        else if (command.startsWith("delete")) {
            int index = Integer.parseInt(command.split("delete ")[1]);
            return new DeleteCommand(index);
        }
        else if (command.startsWith("list")) {
            return new ListCommand();
        }
        else {
            return new ExitCommand();
        }
    }
    private Task parseEvent(String command) throws DukeException {
        String [] commands = command.split("event ");
        if (commands.length > 1) {
            String [] commandsDescription = command.split(" /from ");
            if(commandsDescription.length > 1) {
                String[] commandsEvent = commandsDescription[1].split(" /to ");
                if (commandsEvent.length > 1) {
                    return new Event(commandsDescription[0], commandsEvent[0], commandsEvent[1]);
                }
            }

        }
        return new Event("","","");
    }
    private Task parseDeadline(String command) throws DukeException {
        String [] commands = command.split("deadline ");
        if (commands.length > 1) {
            String [] commandsDescription = commands[1].split(" /by ");
            if(commandsDescription.length > 1)
                return new Deadline(commandsDescription[0], commandsDescription[1]);
        }
        return new Deadline("","");
    }
    private Task parseTodo(String command) throws DukeException {
        if (command.split("todo ").length > 1) {
            return new Todo(command.split("todo ")[1]);
        }
        return new Todo("");
    }
}
