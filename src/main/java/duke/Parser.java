package duke;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Parser {
    public static Command parse(String command) throws DukeException {

        if (command.startsWith("todo")) {
            Task todoCommand = parseTodo(command);
            return new AddCommand(todoCommand);
        }
        else if (command.startsWith("deadline")) {
            Task deadlineCommand = parseDeadline(command);
            return new AddCommand(deadlineCommand);
        }
        else if (command.startsWith("event")) {
            Task eventCommand = parseEvent(command);
            return new AddCommand(eventCommand);
        }
        else if (command.startsWith("mark")) {
            int index;
            if (command.split("mark ").length > 1)
            {
                index = Integer.parseInt(command.split("mark ")[1]);
            }
            else {
                throw new DukeException("index can't be empty");
            }
            return new SetAsDoneCommand(index - 1, true);
        }
        else if (command.startsWith("unmark")) {
            int index;
            if (command.split("unmark ").length > 1)
            {
                index = Integer.parseInt(command.split("unmark ")[1]);
            }
            else {
                throw new DukeException("index can't be empty");
            }
            return new SetAsDoneCommand(index - 1, false);
        }
        else if (command.startsWith("delete")) {
            int index;
            if (command.split("delete ").length > 1)
            {
                index = Integer.parseInt(command.split("delete ")[1]);
            }
            else {
                throw new DukeException("index can't be empty");
            }
            return new DeleteCommand(index - 1);
        }
        else if (command.startsWith("list")) {
            return new ListCommand();
        }
        else if (command.startsWith("find")) {
            String keyword = "";
            if (command.split("find ").length > 1)
            {
                keyword = command.split("find ")[1];
            }
            else {
                throw new DukeException("keyword can't be empty");
            }
            return new FindCommand(keyword);
        }
        else if (command.startsWith("bye")){
            return new ExitCommand();
        }
        else {
            throw new DukeException("Invalid command");
        }

    }
    private static Task parseEvent(String command) throws DukeException {
        String [] commands = command.split("event ");
        if (commands.length > 1) {
            if (!commands[1].contains(" /from") && command.contains("/from")) {
                throw new DukeException("Description can't be empty!");
            }
            String [] commandsDescription = commands[1].split(" /from ");
            if(commandsDescription.length > 1) {
                String[] commandsEvent = commandsDescription[1].split(" /to ");
                if (commandsEvent.length > 1) {
                    return new Event(commandsDescription[0], commandsEvent[0], commandsEvent[1]);
                }
                return new Event(commandsDescription[0], commandsEvent[0], "");
            }
            return new Event(commandsDescription[0],"","");
        }
        return new Event("","","");
    }
    private static Task parseDeadline(String command) throws DukeException {
        String [] commands = command.split("deadline ");
        if (commands.length > 1) {
            if (!commands[1].contains(" /by") && command.contains("/by")) {
                throw new DukeException("Description can't be empty!");
            }
            String [] commandsDescription = commands[1].split(" /by ");
            if(commandsDescription.length > 1)
                return new Deadline(commandsDescription[0], commandsDescription[1]);
            return new Deadline(commandsDescription[0], "");
        }
        return new Deadline("","");
    }
    private static Task parseTodo(String command) throws DukeException {
        if (command.split("todo ").length > 1) {
            return new Todo(command.split("todo ")[1]);
        }
        return new Todo("");
    }
}
