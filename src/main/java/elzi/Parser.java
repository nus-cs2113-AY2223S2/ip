package elzi;

import elzi.command.AddCommand;
import elzi.command.Command;
import elzi.command.DeleteCommand;
import elzi.command.ExitCommand;
import elzi.command.FindCommand;
import elzi.command.ListCommand;
import elzi.command.SetAsDoneCommand;
import elzi.task.Deadline;
import elzi.task.Event;
import elzi.task.Task;
import elzi.task.Todo;

/**
 * @author : Steven A. O. Waskito
 * Class Parser parses the command input
 * Returns command to Elzi.java
 **/
public class Parser {
    /**
     * Parses and handles command input
     * returns the respective command class
     * @param command input command from user
     * @throws ElziException if command is illegal
     */
    public static Command parse(String command) throws ElziException {

        if (command.startsWith("help")) {
            Ui.printHelp();
            return null;
        } else if (command.startsWith("todo")) {
            Task todoCommand = parseTodo(command);
            return new AddCommand(todoCommand);
        } else if (command.startsWith("deadline")) {
            Task deadlineCommand = parseDeadline(command);
            return new AddCommand(deadlineCommand);
        } else if (command.startsWith("event")) {
            Task eventCommand = parseEvent(command);
            return new AddCommand(eventCommand);
        } else if (command.startsWith("mark")) {
            int index;
            if (command.split("mark ").length > 1) {
                String commandIndex = command.split("mark ")[1];
                try {
                    index = Integer.parseInt(commandIndex);
                } catch (NumberFormatException e) {
                    throw new ElziException("Index is not an integer!");
                }
            } else {
                throw new ElziException("index can't be empty");
            }
            return new SetAsDoneCommand(index - 1, true);
        } else if (command.startsWith("unmark")) {
            int index;
            if (command.split("unmark ").length > 1) {
                String commandIndex = command.split("unmark ")[1];
                try {
                    index = Integer.parseInt(commandIndex);
                } catch (NumberFormatException e) {
                    throw new ElziException("Index is not an integer!");
                }
            } else {
                throw new ElziException("index can't be empty");
            }
            return new SetAsDoneCommand(index - 1, false);
        } else if (command.startsWith("delete")) {
            int index;
            if (command.split("delete ").length > 1) {
                index = Integer.parseInt(command.split("delete ")[1]);
            } else {
                throw new ElziException("index can't be empty");
            }
            return new DeleteCommand(index - 1);
        } else if (command.startsWith("list")) {
            if (command.startsWith("list_todo")) {
                return new ListCommand("T");
            } else if (command.startsWith("list_deadline")) {
                return new ListCommand("D");
            } else if (command.startsWith("list_event")) {
                return new ListCommand("E");
            }
            return new ListCommand("N");
        } else if (command.startsWith("find")) {
            String keyword = "";
            if (command.split("find ").length > 1) {
                keyword = command.split("find ")[1];
            } else {
                throw new ElziException("keyword can't be empty");
            }
            return new FindCommand(keyword);
        } else if (command.startsWith("bye")) {
            return new ExitCommand();
        } else {
            throw new ElziException("Invalid command");
        }

    }
    /**
     * Parses and handles event input
     * returns event class
     * @param command input command from user
     * @throws ElziException if command is illegal
     */
    private static Task parseEvent(String command) throws ElziException {
        String [] commands = command.split("event ");
        if (commands.length > 1) {
            if (!commands[1].contains(" /from") && command.contains("/from")) {
                throw new ElziException("Description can't be empty!");
            }
            String [] commandsDescription = commands[1].split(" /from ");
            if (commandsDescription.length > 1) {
                String[] commandsEvent = commandsDescription[1].split(" /to ");
                if (commandsEvent.length > 1) {
                    return new Event(commandsDescription[0], commandsEvent[0], commandsEvent[1]);
                }
                return new Event(commandsDescription[0], commandsEvent[0], "");
            }
            return new Event(commandsDescription[0], "", "");
        }
        return new Event("", "", "");
    }
    /**
     * Parses and handles deadline input
     * returns deadline class
     * @param command input command from user
     * @throws ElziException if command is illegal
     */
    private static Task parseDeadline(String command) throws ElziException {
        String [] commands = command.split("deadline ");
        if (commands.length > 1) {
            if (!commands[1].contains(" /by") && command.contains("/by")) {
                throw new ElziException("Description can't be empty!");
            }
            String [] commandsDescription = commands[1].split(" /by ");
            if (commandsDescription.length > 1) {
                return new Deadline(commandsDescription[0], commandsDescription[1]);
            }
            return new Deadline(commandsDescription[0], "");
        }
        return new Deadline("", "");
    }
    /**
     * Parses and handles todo input
     * returns todo class
     * @param command input command from user
     * @throws ElziException if command is illegal
     */
    private static Task parseTodo(String command) throws ElziException {
        if (command.split("todo ").length > 1) {
            return new Todo(command.split("todo ")[1]);
        }
        return new Todo("");
    }
}
