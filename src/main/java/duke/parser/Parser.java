package duke.parser;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.command.DeadlineCommand;

public class Parser {
    private static final String OOPS = "☹ OOPS!!! ";
    public  Parser() {}

    public Command parseCommand(String userInputCommand) throws IllegalArgumentException {
        String[] args = userInputCommand.split(" ");

        switch (args[0]) {
        case TodoCommand.COMMAND_WORD:
            return parseTodo(args);
        case DeadlineCommand.COMMAND_WORD:
            return parseDeadline(args);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
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
}
