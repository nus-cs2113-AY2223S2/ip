package duke;

import duke.Commands.*;
import dukeException.DukeException;
import tasks.Deadline;
import tasks.Event;

public class Parser {

    public static Command parse(String command) {

        String[] cmds = command.split(" ", 2);
        String cmd = cmds[0];
        Command tmpCommand = null;
        boolean isParse = true;
        System.out.println("Here " + cmd);
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
            System.out.println("Here in caseFind");

            tmpCommand = parseFind(cmds[1]);
            break;
        default:
            return new Command();
        }

        return tmpCommand;

    }

    public static Command parseFind(String input) {
        System.out.println("Here in parseFind");
        return new FindCommand(input);
    }

    public static Command parseToDo(String input) {
        return new ToDoCommand(input, false);
    }

    public static Command parseDeadline(String input) {
        int idx = input.indexOf("/by");
        System.out.println(input);
        String desc = input.substring(0, idx);
        String by = input.substring(idx + 3);
        Deadline tsk = null;
        return new DeadLineCommand(desc, false, by);
    }

    public static Command parseDelete(String input) {
        int dIdx = Integer.parseInt(input);
        return new DeleteCommand(dIdx - 1);
    }

    public static Command parseEvent(String input) {
        int idx = input.indexOf("/from");
        int idx1 = input.indexOf("/to");
        String desc = input.substring(0, idx);
        String start = input.substring(idx + 5, idx1);
        String end = input.substring(idx1 + 3);
        return new EventCommand(desc, false, start, end);
    }

    public static Command parseExit() {
        return new ExitCommand();
    }

    public static Command parseList() {
        return new ListCommand();
    }

    public static Command parseMark(String input) {
        int dIdx = Integer.parseInt(input);
        return new MarkCommand(dIdx - 1);
    }

    public static Command parseUnMark(String input) {
        int dIdx = Integer.parseInt(input);
        return new UnMarkCommand(dIdx - 1);
    }


}
