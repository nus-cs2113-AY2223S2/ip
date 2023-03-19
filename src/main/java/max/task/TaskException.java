package max.task;

/**
 * This exception is thrown when there is a logical error dealing with tasks
 */
public class TaskException extends Exception {
    public TaskException(String errorMessage) {
        super(errorMessage);
    }
}