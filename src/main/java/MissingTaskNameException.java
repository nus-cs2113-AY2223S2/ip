public class MissingTaskNameException extends DukeException{

    public MissingTaskNameException (String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return errorMessage;
    }
}
