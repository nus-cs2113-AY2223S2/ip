package shizuka;

public class Parser {
    /**
     * Splits the input string into command keyword and arguments.
     *
     * @param args User input string
     * @return Array of size 2, with command keyword at index 0 and arguments at index 1
     */
    public static String[] parseCommand(String args) {
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
}
