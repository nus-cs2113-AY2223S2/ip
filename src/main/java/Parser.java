/**
 * Represents a parser which breaks down the user input to determine if the right
 * command formats are given and which commands should be given to the Duke ChatBot.
 */
public class Parser {
    protected String firstWord;
    protected String restOfCommand;

    /**
     * Breaks down the user command into 2 parts: the first word and the rest of the input.
     * Subsequently, initialise the Parser object with these 2 parts.
     * @param fullCommand The full input entered by the user
     */
    public Parser(String fullCommand) {
        // Get first word from input
        int indexOfFirstSpace = fullCommand.indexOf(" ");
        if (indexOfFirstSpace != -1) {
            // Separate first word from the rest of input
            this.firstWord = fullCommand.substring(0, indexOfFirstSpace);
            this.restOfCommand = fullCommand.substring(indexOfFirstSpace + 1);
        } else {
            // Only 1 word in input
            this.firstWord = fullCommand;
            this.restOfCommand = "";
        }
    }

    /**
     * Determine the type of command entered by the user based on the first word.
     * @return A Command object depending on the type of command entered
     * @throws DukeException If command entered is not recognised or of wrong format
     */
    public Command parse() throws DukeException {
        switch (firstWord) {
        case "delete":
            return new DeleteCommand(firstWord, restOfCommand);
        case "list":
            if (restOfCommand.length() != 0) {
                throw new DukeException();
            }
            return new ListCommand(firstWord, restOfCommand);
        case "find":
            return new FindCommand(firstWord, restOfCommand);
        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "event":
            return new AddCommand(firstWord, restOfCommand);
        case "mark":
            return new MarkCommand(firstWord, restOfCommand);
        case "unmark":
            return new UnmarkCommand(firstWord, restOfCommand);
        case "bye":
            if (restOfCommand.length() != 0) {
                throw new DukeException();
            }
            return new ExitCommand(firstWord, restOfCommand);
        default:
            throw new DukeException();
        }
    }
}

