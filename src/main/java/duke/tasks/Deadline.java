package duke.tasks;

public class Deadline extends Task {
    private String dueDate;

    public Deadline(String taskName, String dueDate) {
        super(taskName); // invoke superclass constructor
        this.dueDate = dueDate;
    }

    @Override
    public String listTask() {
        if (getIsComplete()) {
            return "[D][X] " + getTaskName() + " (by: " + dueDate + ')';
        } else {
            return "[D][] " + getTaskName() + " (by: " + dueDate + ')';
        }
    }
}
