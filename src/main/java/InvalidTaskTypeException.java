public class InvalidTaskTypeException extends DukeException {

    public InvalidTaskTypeException (String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return errorMessage;
    }
}
