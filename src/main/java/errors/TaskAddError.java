package errors;

public class TaskAddError extends Exception {

    public TaskAddError() {
        super("There seems to be some error with adding a task\n");
    }

    public TaskAddError(String message) {
        super(message);
    }
}
