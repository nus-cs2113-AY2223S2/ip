package duke.tasks;

public class Deadline extends Task {
    private String dueDate;

    public Deadline(String taskName, String dueDate) {
        super(taskName); // invoke superclass constructor
        this.dueDate = dueDate;
    }

    @Override
    public String listTask() {
        return taskTypeBoxFormat() + markedBoxFormat() + " " + getTaskName() + " (by: " + dueDate + ')';
    }

    @Override
    public String taskTypeBoxFormat() {
        return "[D]";
    }

}
