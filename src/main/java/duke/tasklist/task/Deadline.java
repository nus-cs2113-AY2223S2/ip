package duke.tasklist.task;

public class Deadline extends Task {
    private static final String DEADLINE_ICON = "D";

    private String by;

    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[" + DEADLINE_ICON + "]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toFile() {
        return DEADLINE_ICON + " " + super.toFile() + " | " + by;
    }
}
