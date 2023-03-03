package parser;

import exceptions.IncompleteInputException;
import command.Command;
import command.CommandType;

/**
 * The class contains parsing methods which separates user input into commands
 * and description.
 */
public class Parser {

    public static final String DEADLINE_MISSING_DESCRIPTION_BY = "Deadline is missing the \"DESCRIPTION\" or \"BY\"!\n";
    public static final String DEADLINE_MISSING_DESCRIPTION = "Deadline is missing \"DESCRIPTION\"!\n";
    public static final String DEADLINE_MISSING_BY = "Deadline is missing \"BY\"!\n";
    public static final String EVENT_MISSING_DESCRIPTION_FROM_TO = "Event is missing the \"DESCRIPTION\" or \"FROM\" or \"TO\"!\n";
    public static final String EVENT_MISSING_DESCRIPTION = "Event is missing \"DESCRIPTION\"!\n";
    public static final String EVENT_MISSING_FROM = "Event is missing \"FROM\"!\n";
    public static final String EVENT_MISSING_TO = "Event is missing \"TO\"!\n";

    /**
     * Returns a <code>Command</code> type which contains the command to be
     * executed and its full description to support its execution. It takes
     * in a string representing the user input.
     *
     * @param line the input line by user.
     * @return a <code>Command</code> containing the command to be executed.
     */
    public static Command parseCommands(String line) {
        String[] lineSpaced = line.split(" ");
        String fullDescription = "";
        if (lineSpaced.length > 1) {
            for (int i = 2; i < lineSpaced.length; i++) {
                lineSpaced[1] = lineSpaced[1].concat(" " + lineSpaced[i]);
            }
            fullDescription = lineSpaced[1];
        }
        CommandType type;
        switch (lineSpaced[0]) {
        case "list":
            type = CommandType.LIST;
            break;
        case "mark":
            type = CommandType.MARK;
            break;
        case "unmark":
            type = CommandType.UNMARK;
            break;
        case "todo":
            type = CommandType.TODO;
            break;
        case "deadline":
            type = CommandType.DEADLINE;
            break;
        case "event":
            type = CommandType.EVENT;
            break;
        case "delete":
            type = CommandType.DELETE;
            break;
        case "help":
            type = CommandType.HELP;
            break;
        case "find":
            type = CommandType.FIND;
            break;
        case "bye":
            type = CommandType.EXIT;
            break;
        default:
            type = CommandType.UNKNOWN;
        }
        return new Command(type, fullDescription);
    }

    /**
     * Returns an Array of Strings containing the parsed full description
     * of a <code>Deadline</code> task into its description and due date.
     *
     * @param description the full description of the <code>Deadline</code> task.
     * @return parsed Array of Strings containing the deadline's description and due date.
     * @throws IncompleteInputException if full description input is missing the description or due date or both.
     */
    public static String[] parseDeadline(String description) throws IncompleteInputException {
        String[] parsed = description.split(" /by ");
        if (parsed.length < 2) {
            throw new IncompleteInputException(DEADLINE_MISSING_DESCRIPTION_BY);
        }
        if (parsed[0].isEmpty()) {
            throw new IncompleteInputException(DEADLINE_MISSING_DESCRIPTION);
        }
        if (parsed[1].isEmpty()) {
            throw new IncompleteInputException(DEADLINE_MISSING_BY);
        }
        return parsed;
    }

    /**
     * Returns an Array of Strings containing the parsed full description
     * of a <code>Event</code> task into its description, start date and end date.
     *
     * @param description the full description of the <code>Event</code> task.
     * @return parsed Array of Strings containing the deadline's description, start date and end date.
     * @throws IncompleteInputException if full description input is missing the description or start
     * date or end date or all three.
     */
    public static String[] parseEvent(String description) throws IncompleteInputException {
        String[] parsed = description.split(" /", 3);
        if (parsed.length < 3) {
            throw new IncompleteInputException(EVENT_MISSING_DESCRIPTION_FROM_TO);
        }
        if (parsed[0].isEmpty()) {
            throw new IncompleteInputException(EVENT_MISSING_DESCRIPTION);
        }
        parsed[1] = parsed[1].substring(5);
        if (parsed[1].isEmpty()) {
            throw new IncompleteInputException(EVENT_MISSING_FROM);
        }
        parsed[2] = parsed[2].substring(3);
        if (parsed[2].isEmpty()) {
            throw new IncompleteInputException(EVENT_MISSING_TO);
        }
        return parsed;
    }
}
