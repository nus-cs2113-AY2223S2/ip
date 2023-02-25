package duke.tasklist.exception;

public class TaskNotFoundException extends IllegalArgumentException {
    public TaskNotFoundException() {
        super("Task not found!");
    }

    public TaskNotFoundException(String msg) {
        super(msg);
    }

}
