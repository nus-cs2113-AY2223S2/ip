package duke.parser;

import java.util.UnknownFormatConversionException;

import duke.command.TodoCommand;
import duke.command.DeadlineCommand;
import duke.command.EventCommand;
import duke.tasklist.task.Deadline;
import duke.tasklist.task.Event;
import duke.tasklist.task.Task;
import duke.tasklist.task.Todo;


public class CsvParser {
    private static final String OTHER_THAN_QUOTE_REGEX = " [^\"] ";
    private static final String QUOTED_STRING_REGEX = String.format(" \" %s* \" ", OTHER_THAN_QUOTE_REGEX);
    private static final String BETWEEN_QUOTES_REGEX = String.format("(?x) " // enable comments, ignore white spaces
                    + ",                         " // match a comma
                    + "(?=                       " // start positive look ahead
                    + "  (?:                     " //   start non-capturing group 1
                    + "    %s*                   " //     match 'otherThanQuote' zero or more times
                    + "    %s                    " //     match 'quotedString'
                    + "  )*                      " //   end group 1 and repeat it zero or more times
                    + "  %s*                     " //   match 'otherThanQuote'
                    + "  $                       " // match the end of the string
                    + ")                         ", // stop positive look ahead
            OTHER_THAN_QUOTE_REGEX, QUOTED_STRING_REGEX, OTHER_THAN_QUOTE_REGEX);

    public CsvParser() {}

    public Task parseLine(String line) {
        String[] args = splitCsvLine(line);
        switch (args[0]) {
        case TodoCommand.COMMAND_WORD:
            return parseTodo(args);
        case DeadlineCommand.COMMAND_WORD:
            return parseDeadline(args);
        case EventCommand.COMMAND_WORD:
            return parseEvent(args);
        default:
            throw new UnknownFormatConversionException("Unknown command word!");
        }
    }

    private String[] splitCsvLine(String line) {
        String[] args = line.split(BETWEEN_QUOTES_REGEX, -1);
        for (int i = 0; i < args.length; ++i) {
            String arg = args[i];
            if (arg.charAt(0) == '\"' && arg.charAt(arg.length()-1) == '\"') {
                args[i] = arg.substring(1, arg.length()-1);
            }
        }
        return args;
    }

    private Task parseTodo(String[] args) throws IllegalArgumentException {
        assert args[0].equals(TodoCommand.COMMAND_WORD);
        try {
            boolean isMarked = (Integer.parseInt(args[1]) == 1);
            String content = args[2];
            return new Todo(content, isMarked);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Format conversion failed.");
        }
    }

    private Task parseDeadline(String[] args) throws IllegalArgumentException {
        assert args[0].equals(DeadlineCommand.COMMAND_WORD);
        try {
            boolean isMarked = (Integer.parseInt(args[1]) == 1);
            String content = args[2];
            String byTime = args[3];
            return new Deadline(content, byTime, isMarked);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Format conversion failed.");
        }
    }

    private Task parseEvent(String[] args) throws IllegalArgumentException {
        assert args[0].equals(EventCommand.COMMAND_WORD);
        try {
            boolean isMarked = (Integer.parseInt(args[1]) == 1);
            String content = args[2];
            String fromTime = args[3];
            String toTime = args[4];
            return new Event(content, fromTime, toTime, isMarked);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Format conversion failed.");
        }
    }
}
