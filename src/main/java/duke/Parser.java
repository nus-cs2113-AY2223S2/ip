package duke;

public class Parser {
    /**
     * Creates a new command object given the full command string
     * @param fullCommand the command string as entered by the user
     * @return the created command object
     * @throws IllegalCommandException command is invalid or parameter flags are invalid
     * @throws IllegalParameterException command has valid format but has invalid parameter values
     */
    public static Command parse(String fullCommand) throws IllegalCommandException, IllegalParameterException {
        return new Command(fullCommand);
    }
}
