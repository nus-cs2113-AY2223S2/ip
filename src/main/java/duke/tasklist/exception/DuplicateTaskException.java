package duke.tasklist.exception;

public class DuplicateTaskException extends Exception {
    public DuplicateTaskException() {
        super("Duplicate task(es)!");
    }
    public DuplicateTaskException(String msg) {
        super(msg);
    }
}
