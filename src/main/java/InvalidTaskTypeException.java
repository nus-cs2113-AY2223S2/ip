public class InvalidTaskTypeException extends DukeException {

    public InvalidTaskTypeException (String errorMsg) {
        super(errorMsg);
    }

    @Override
    public String toString() {
        return errorMsg;
    }
}
