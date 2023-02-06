public class DukeException extends Exception {
    protected String errorMessage;

    public DukeException (String errorMsg) {
        this.errorMessage = errorMessage;
    }
}
