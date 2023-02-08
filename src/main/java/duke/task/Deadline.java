package duke.task;

public class Deadline extends Task {
    protected final String taskName;
    protected String dueBy;

    public Deadline(String description, String dueBy) {
        super(description);
        this.taskName = description;
        this.dueBy = dueBy;
    }

    public String returnCommand() {
        return "deadline " + taskName + " /by " + dueBy;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueBy + ")";
    }
}
