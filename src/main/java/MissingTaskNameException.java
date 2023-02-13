/**
 * Represents an exception when the user inputs a correct Task type but missing task name.
 */
public class MissingTaskNameException extends DukeException {
    public MissingTaskNameException (String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return errorMessage;
    }
}
