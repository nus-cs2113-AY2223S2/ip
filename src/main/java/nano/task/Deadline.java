package nano.task;

public class Deadline extends Task{
    private static final String TASK_TYPE_TAG = "[D]";
    private String dueDate;
    public Deadline(String name, String dueDate) {
        super(name);
        this.dueDate = dueDate;
    }


    public String toString() {
        return TASK_TYPE_TAG + super.toString() + " (by: " + dueDate + ")";
    }
}
