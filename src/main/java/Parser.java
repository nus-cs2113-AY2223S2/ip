public class Parser {
    protected String firstWord;
    protected String restOfCommand;

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

    public Command parse() throws DukeException {
        switch (firstWord) {
        case "delete":
            return new DeleteCommand(firstWord, restOfCommand);
        case "list":
            return new ListCommand(firstWord, restOfCommand);
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
            return new ExitCommand(firstWord, restOfCommand);
        default:
            throw new DukeException();
        }
    }
}

