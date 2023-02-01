public class MissingTaskNameException extends DukeException{

    public MissingTaskNameException (String errorMsg) {
        super(errorMsg);
    }

    @Override
    public String toString() {
        return errorMsg;
    }
}
