public class DukeException extends Exception {
    protected String errorMsg;

    public DukeException (String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
