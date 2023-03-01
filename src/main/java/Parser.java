/**
 * Represents a parser to parse user input for execution.
 */
public class Parser {

    /**
     * Returns the command and arguments from user input.
     *
     * @return c The parsed command from user input
     */
    public static Command parseInput(String action) {

        Command c = new Command(action.split(" ", 2));

        return c;
    }

}
