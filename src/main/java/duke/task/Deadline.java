package duke.task;

public class Deadline extends Task {

    public static final String DEADLINE_LABEL = "D";
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        increaseCounter();
    }

    public String getType() {
        return "deadline";
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String toString() {
        return "[" + DEADLINE_LABEL + "][" + getStatus() + "] " + description + " (by: " + by + ")";
    }
}
