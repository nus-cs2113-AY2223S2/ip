package Arsdorint.task;

public class Todo extends Task {
    public static final String TYPE_TODO = "T";
    public Todo(String description) {
        super(description);
        this.taskName = TYPE_TODO;
        this.taskType = "[T]";
    }

    /**
     * Initiate with status
     */
    public Todo(boolean status, String description) {
        this(description);
        this.isDone = status;
    }
}
