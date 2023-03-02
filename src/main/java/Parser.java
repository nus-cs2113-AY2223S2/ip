import Exceptions.IncompleteInputException;

public class Parser {
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

    public static String[] parseDeadline(String description) throws IncompleteInputException {
        String[] parsed = description.split(" /by ");
        if (parsed.length < 2) {
            throw new IncompleteInputException("Deadline is missing the \"DESCRIPTION\" or \"BY\"!\n");
        }
        if (parsed[0].isEmpty()) {
            throw new IncompleteInputException("Deadline is missing \"DESCRIPTION\"!\n");
        }
        if (parsed[1].isEmpty()) {
            throw new IncompleteInputException("Deadline is missing \"BY\"!\n");
        }
        return parsed;
    }

    public static String[] parseEvent(String description) throws IncompleteInputException {
        String[] parsed = description.split(" /", 3);
        if (parsed.length < 3) {
            throw new IncompleteInputException("Event is missing the \"DESCRIPTION\" or \"FROM\" or \"TO\"!\n");
        }
        if (parsed[0].isEmpty()) {
            throw new IncompleteInputException("Event is missing \"DESCRIPTION\"!\n");
        }
        parsed[1] = parsed[1].substring(5);
        if (parsed[1].isEmpty()) {
            throw new IncompleteInputException("Event is missing \"FROM\"!\n");
        }
        parsed[2] = parsed[2].substring(3);
        if (parsed[2].isEmpty()) {
            throw new IncompleteInputException("Event is missing \"TO\"!\n");
        }
        return parsed;
    }
}
