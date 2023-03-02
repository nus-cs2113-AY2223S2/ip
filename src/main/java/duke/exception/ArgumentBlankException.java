package duke.exception;
import duke.Command;
/**
 * Exception thrown when user inputs a command but leaves one or more required arguments blank
 */
public class ArgumentBlankException extends Exception {
    public String argumentType;
    public String commandType;
    public ArgumentBlankException(String commandType, String argumentType) {
        this.argumentType = argumentType;
        this.commandType = commandType;
    }
}
