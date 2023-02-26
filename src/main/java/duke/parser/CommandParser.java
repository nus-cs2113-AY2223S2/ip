package duke.parser;

import duke.command.*;

import java.util.ArrayList;
import java.util.List;

public class CommandParser {
    private static final String OOPS = "☹ OOPS!!! ";
    public CommandParser() {}

    public Command parseCommand(String userInputCommand) throws IllegalArgumentException {
        String[] args = userInputCommand.split(" ");

        switch (args[0]) {
        case TodoCommand.COMMAND_WORD:
            return parseTodo(args);
        case DeadlineCommand.COMMAND_WORD:
            return parseDeadline(args);
        case EventCommand.COMMAND_WORD:
            return parseEvent(args);
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case MarkCommand.COMMAND_WORD:
            return parseMark(args);
        case UnmarkCommand.COMMAND_WORD:
            return parseUnmark(args);
        case DeleteCommand.COMMAND_WORD:
            return parseDelete(args);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case HelpCommand.COMMAND_WORD: //fall through
        default:
            return new HelpCommand();
        }
    }

    private Command parseTodo(String[] args) throws  IllegalArgumentException {
        assert args[0].equals("todo");
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < args.length; ++i) {
            sb.append(args[i]).append(" ");
        }
        if (sb.toString().isEmpty()) {
            throw new IllegalArgumentException(OOPS + "The description of a todo cannot be empty.");
        }
        String content = sb.toString().trim();
        return new TodoCommand(content);
    }

    private Command parseDeadline(String[] args) throws IllegalArgumentException {
        assert args[0].equals("deadline");
        StringBuilder contentSb = new StringBuilder();
        StringBuilder bySb = new StringBuilder();

        int byIndex = -1;
        for (int i = 0; i < args.length; ++i) {
            if (args[i].equals("/by")) {
                byIndex = i;
            }
        }

        if (byIndex == -1) {
            throw new IllegalArgumentException(OOPS + "Cannot find the by time of the event.");
        }

        for (int i = 1; i < args.length; ++i) {
            if (i == byIndex) {
                continue;
            }
            if (i < byIndex) {
                contentSb.append(args[i]).append(" ");
            } else {
                bySb.append(args[i]).append(" ");
            }
        }

        if (contentSb.toString().isEmpty()) {
            throw new IllegalArgumentException(OOPS + "The description of a deadline cannot be empty.");
        } else if (bySb.toString().isEmpty()) {
            throw new IllegalArgumentException(OOPS + "The by time of a deadline cannot be empty.");
        }

        String content = contentSb.toString().trim();
        String by = bySb.toString().trim();

        return new DeadlineCommand(content, by);
    }

    private Command parseEvent(String[] args) throws IllegalArgumentException{
        assert args[0].equals("event");
        StringBuilder contentSb = new StringBuilder();
        StringBuilder fromSb = new StringBuilder();
        StringBuilder toSb = new StringBuilder();

        int fromIndex = -1;
        int toIndex = -1;
        for (int i = 0; i < args.length; ++i) {
            if (args[i].equals("/from")) {
                fromIndex = i;
            } else if (args[i].equals("/to")) {
                toIndex = i;
            }
        }

        if (fromIndex == -1 || toIndex == -1) {
            throw new IllegalArgumentException(OOPS + "Cannot find from or to time of the event!");
        }

        for (int i = 1; i < args.length; ++i) {
            if (i < fromIndex) {
                contentSb.append(args[i]).append(" ");
            } else if (i > fromIndex && i < toIndex) {
                fromSb.append(args[i]).append(" ");
            } else if (i > toIndex) {
                toSb.append(args[i]).append(" ");
            }
        }

        if (contentSb.toString().isEmpty() || fromSb.toString().isEmpty() || toSb.toString().isEmpty()) {
            throw new IllegalArgumentException(OOPS + "The content/from/to of an event cannot be empty!");
        }

        String content = contentSb.toString().trim();
        String from = fromSb.toString().trim();
        String to = toSb.toString().trim();

        return new EventCommand(content, from, to);
    }

    private Command parseMark(String[] args) throws IllegalArgumentException {
        assert args[0].equals("mark");
        if (args.length == 1) {
            throw new IllegalArgumentException(OOPS + "Cannot find task to mark.");
        }
        if (args.length == 2) {
            try {
                return new MarkCommand(Integer.parseInt(args[1]));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(OOPS + args[1] + " is not a valid number!");
            }
        } else {
            return new MarkCommand(parseArgsToInt(args, 1));
        }
    }
    private List<Integer> parseArgsToInt(String[] args, int startId) {
        List<Integer> toParse = new ArrayList<>();
        for (int i = startId; i < args.length; ++i) {
            try {
                int id = Integer.parseInt(args[i]);
                toParse.add(id);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(OOPS + args[i] + " is not a valid number!");
            }
        }
        return toParse;
    }
    private Command parseUnmark(String[] args) throws IllegalArgumentException {
        assert args[0].equals("unmark");
        if (args.length == 1) {
            throw new IllegalArgumentException(OOPS + "Cannot find task to mark.");
        }
        if (args.length == 2) {
            try {
                return new UnmarkCommand(Integer.parseInt(args[1]));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(OOPS + args[1] + " is not a valid number!");
            }
        } else {
            return new UnmarkCommand(parseArgsToInt(args, 1));
        }
    }

    private Command parseDelete(String[] args) throws IllegalArgumentException {
        assert args[0].equals(DeleteCommand.COMMAND_WORD);
        if (args.length == 1) {
            throw new IllegalArgumentException(OOPS + "Cannot find task to delete.");
        }
        if (args.length == 2) {
            try {
                return new DeleteCommand(Integer.parseInt(args[1]));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(OOPS + args[1] + " is not a valid number!");
            }
        } else {
            return new UnmarkCommand(parseArgsToInt(args, 1));
        }
    }
}
