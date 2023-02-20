/**
 * Represents an exception that is thrown when the user inputs a type of task that is not Todo, Event or Deadline.
 */
public class InvalidTaskTypeException extends DukeException {
    public InvalidTaskTypeException (String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return errorMessage;
    }
}
