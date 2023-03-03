package duke;

import duke.Commands.*;
import dukeException.DukeException;
import tasks.Deadline;
import tasks.Event;

/**
 * The Parser class is responsible for parsing user input and generating the appropriate commands for Duke to execute.
 */
public class Parser {
    /**
     * Parses the given command and generates the appropriate command for Duke to execute.
     *
     * @param command the command to parse
     * @return the appropriate command for Duke to execute
     */
    public static Command parse(String command) {

        String[] cmds = command.split(" ", 2);
        String cmd = cmds[0];
        Command tmpCommand = null;
        if ((!cmd.equals(ExitCommand.COMMAND_WORD) &&
                cmd.equals(ListCommand.COMMAND_WORD) == false) &&
                cmds.length == 1) {
            System.out.print("Malformed input, ");
            return new Command();
        }
        boolean isParse = true;
        switch (cmd) {
        case DeadLineCommand.COMMAND_WORD:
            tmpCommand = parseDeadline(cmds[1]);
            break;
        case DeleteCommand.COMMAND_WORD:
            tmpCommand = parseDelete(cmds[1]);
            break;
        case EventCommand.COMMAND_WORD:
            tmpCommand = parseEvent(cmds[1]);
            break;
        case ExitCommand.COMMAND_WORD:
            tmpCommand = parseExit();
            break;
        case ListCommand.COMMAND_WORD:
            tmpCommand = parseList();
            break;
        case MarkCommand.COMMAND_WORD:
            tmpCommand = parseMark(cmds[1]);
            break;
        case UnMarkCommand.COMMAND_WORD:
            tmpCommand = parseUnMark(cmds[1]);
            break;
        case ToDoCommand.COMMAND_WORD:
            tmpCommand = parseToDo(cmds[1]);
            break;
        case FindCommand.COMMAND_WORD:
            tmpCommand = parseFind(cmds[1]);
            break;
        default:
            return new Command();
        }

        return tmpCommand;

    }

    /**
     * Parses the user input and generates a FindCommand for Duke to execute.
     *
     * @param input the user input
     * @return a FindCommand for Duke to execute
     */
    public static Command parseFind(String input) {
        return new FindCommand(input);
    }

    /**
     * Parses the user input and generates a ToDoCommand for Duke to execute.
     *
     * @param input the user input
     * @return a ToDoCommand for Duke to execute
     */
    public static Command parseToDo(String input) {
        if (input.equals("")) {
            System.out.println("Todo description is empty :(");
            return new Command();
        }
        return new ToDoCommand(input, false);
    }

    /**
     * Parses the user input and generates a DeadLineCommand for Duke to execute.
     *
     * @param input the user input
     * @return a DeadLineCommand for Duke to execute
     */
    public static Command parseDeadline(String input) {
        int idx = input.indexOf("/by");
        if (idx == -1) {
            System.out.println("Deadline by time is empty :(");
            return new Command();
        }
        System.out.println(input);
        String desc = input.substring(0, idx);
        String by = input.substring(idx + 3);
        Deadline tsk = null;
        if (desc.equals("")) {
            System.out.println("Deadline description is empty :(");
        }
        return new DeadLineCommand(desc, false, by);
    }

    /**
     * Parses the user input and generates a DeleteCommand for Duke to execute.
     *
     * @param input the user input
     * @return a DeleteCommand for Duke to execute
     */
    public static Command parseDelete(String input) {
        if (input.equals("")) {
            System.out.println("No valid index found");
            return new Command();
        }
        int dIdx = Integer.parseInt(input);
        return new DeleteCommand(dIdx - 1);
    }

    /**
     * Parses the user input and generates an EventCommand for Duke to execute.
     *
     * @param input the user input
     * @return an EventCommand for Duke to execute
     */
    public static Command parseEvent(String input) {
        if (input.equals("")) {
            return new Command();
        }
        int idx = input.indexOf("/from");
        if (idx == -1) {
            System.out.println("Event from time is empty :(");
            return new Command();
        }
        int idx1 = input.indexOf("/to");
        if (idx1 == -1) {
            System.out.println("Event to time is empty :(");
            return new Command();
        }
        String desc = input.substring(0, idx);
        String start = input.substring(idx + 5, idx1);
        String end = input.substring(idx1 + 3);
        if (desc.equals("")) {
            System.out.println("Event description is empty :(");
            return new Command();
        }
        return new EventCommand(desc, false, start, end);
    }

    /**
     * Parses the "exit" command and returns an instance of ExitCommand.
     *
     * @return An instance of ExitCommand.
     */
    public static Command parseExit() {
        return new ExitCommand();
    }

    /**
     * Parses the "list" command and returns an instance of ListCommand.
     *
     * @return An instance of ListCommand.
     */
    public static Command parseList() {
        return new ListCommand();
    }

    /**
     * Parses the "mark" command and returns an instance of MarkCommand.
     *
     * @param input The user input containing the index of the task to mark.
     * @return An instance of MarkCommand.
     */
    public static Command parseMark(String input) {
        if (input.equals("")) {
            System.out.println("No valid index found");
            return new Command();
        }
        int dIdx = Integer.parseInt(input);
        return new MarkCommand(dIdx - 1);
    }

    /**
     * Parses the "unmark" command and returns an instance of UnMarkCommand.
     *
     * @param input The user input containing the index of the task to unmark.
     * @return An instance of UnMarkCommand.
     */
    public static Command parseUnMark(String input) {
        if (input.equals("")) {
            System.out.println("No valid index found");
            return new Command();
        }
        int dIdx = Integer.parseInt(input);
        return new UnMarkCommand(dIdx - 1);
    }


}
