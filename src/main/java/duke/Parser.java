package duke;

import duke.exception.EmptyTaskException;
import duke.exception.IllegalCommandException;
import duke.exception.InvalidDeadline;
import duke.exception.InvalidEvent;

public class Parser {

    public static final String COMMAND_EXIT_WORD = "bye";
    public static final String COMMAND_HELP_WORD = "help";
    public static final String COMMAND_LIST_WORD = "list";
    public static final String COMMAND_MARK_WORD = "mark";
    public static final String COMMAND_UNMARK_WORD = "unmark";
    public static final String COMMAND_DELETE_WORD = "delete";
    public static final String COMMAND_TODO_WORD = "todo";
    public static final String COMMAND_DEADLINE_WORD = "deadline";
    public static final String COMMAND_EVENT_WORD = "event";

    public static Command parseCommand(String userCommand)
            throws EmptyTaskException, IllegalCommandException, NumberFormatException, InvalidDeadline, InvalidEvent {
        final String[] split = userCommand.trim().split("\\s+", 2);
        String command = split[0];
        switch (command) {
        case COMMAND_EXIT_WORD:
            return new ExitCommand();
        case COMMAND_HELP_WORD:
            return new HelpCommand();
        case COMMAND_LIST_WORD:
            return new ListCommand();
        case COMMAND_MARK_WORD:
        case COMMAND_UNMARK_WORD:
        case COMMAND_DELETE_WORD:
            if (split.length != 2) {
                throw new NumberFormatException();
            }
            return new MarkAndDelCommand(command, split[1]);
        case COMMAND_TODO_WORD:
        case COMMAND_DEADLINE_WORD:
        case COMMAND_EVENT_WORD:
            if (split.length != 2) {
                throw new EmptyTaskException();
            }
            return new AddCommand(command, split[1]);
        default:
            throw new IllegalCommandException();
        }
    }

    public static String[] parseDeadline(String param) throws InvalidDeadline {
        String[] split = param.trim().split("\\s/by\\s", 2);
        if (split.length != 2) {
            throw new InvalidDeadline();
        }
        return split;
    }

    public static String[] parseEvent(String param) throws InvalidEvent {
        String[] split = param.trim().split("\\s/from\\s|\\s/to\\s", 3);
        if (split.length != 3) {
            throw new InvalidEvent();
        }
        return split;
    }
}
