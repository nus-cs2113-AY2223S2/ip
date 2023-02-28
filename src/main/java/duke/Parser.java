package duke;

public class Parser {
    public static Command parse(String fullCommand) throws IllegalCommandException, IllegalParameterException {
        return new Command(fullCommand);
    }
}
