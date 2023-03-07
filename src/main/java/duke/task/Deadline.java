package duke.task;

public class Deadline extends Task {
    private String by;

    public Deadline(String description, String deadline) {
        super(description);
        by = deadline;
    }

    public void setDeadline(String deadline) {
        by = deadline;
    }

    public String getDeadline() {
        return by;
    }

    @Override
    public String toSaveString() {
        return super.toSaveString() + " /by " + getDeadline() + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
