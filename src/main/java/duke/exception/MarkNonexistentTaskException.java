package duke.exception;

/**
 * Exception thrown when user tries to access a task at an index which is out of range of the current list
 */
public class MarkNonexistentTaskException extends Exception {
    public int taskIndex;
    public MarkNonexistentTaskException(int taskIndex) {
        this.taskIndex = taskIndex;
    }
}

