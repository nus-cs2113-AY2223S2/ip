package duke.exception;

import java.util.Arrays;

public class DukeException extends Exception {
    private static final String DUKE_PREFIX = "duke.Duke Error: ";


    public static String[] parseCommand (String command) throws InvalidCommandException{
        String[] commandArray = command.split(" ");
        if (commandArray.length < 2){
            throw new InvalidCommandException("Description of  cannot be empty!");
        }
        return Arrays.copyOfRange(commandArray, 1, commandArray.length);
    }

    public DukeException(String message) {
        super(DUKE_PREFIX + message);
    }

}
