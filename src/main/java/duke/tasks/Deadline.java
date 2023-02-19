package duke.tasks;

public class Deadline extends Task implements java.io.Serializable {
    private String dueDate;

    public Deadline(String taskName, String dueDate) {
        super(taskName);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return taskTypeBoxFormat() + markedBoxFormat() + " " + getTaskName() + " (by: " + dueDate + ')';
    }

    @Override
    public String taskTypeBoxFormat() {
        return "[D]";
    }

}
