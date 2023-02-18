package duke.exception;
public class MarkNonexistentTaskException extends Exception {
    public int taskIndex;
    public MarkNonexistentTaskException(int taskIndex) {
        this.taskIndex = taskIndex;
    }
}

