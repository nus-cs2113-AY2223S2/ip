package shizuka;

public class Parser {
    /**
     * Splits the input string into command keyword and arguments.
     *
     * @param args User input string
     * @return Array of size 2, with command keyword at index 0 and arguments at index 1
     */
    public static String[] parseKeyword(String args) {
        return args.split(" ", 2);
    }

    /**
     * Parses the task number from the input string.
     *
     * @param args User input string
     * @return Task number
     */
    public static int parseNumber(String args) {
        int endIndex = args.indexOf(' ') + 1;
        return Integer.parseInt(args.substring(endIndex));
    }

    public static Command parseCommand(String userInput) throws ArrayIndexOutOfBoundsException {
        String keyword = parseKeyword(userInput.trim())[0];
        switch (keyword) {
        case "bye":
        case "list":
        case "save":
            return new Command(keyword);
        case "mark":
        case "unmark":
        case "delete":
            try {
                String args = parseKeyword(userInput.trim())[1];
                int taskNum = Parser.parseNumber(args);
                return new Command(keyword, taskNum);
            } catch (ArrayIndexOutOfBoundsException e) {
                UI.noArgsError();
                break;
            }
        case "todo":
        case "deadline":
        case "event":
        case "find":
            try {
                String args = parseKeyword(userInput.trim())[1];
                return new Command(keyword, args);
            } catch (ArrayIndexOutOfBoundsException e) {
                UI.noArgsError();
                break;
            }
        default:
        }
        return new Command("invalid");
    }
}
