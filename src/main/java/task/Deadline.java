package task;

public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description, 'D');
        this.by = by;
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, 'D', isDone);
        this.by = by;
    }

    @Override
    public String getListDescription() {
        return "[" + getType() + "]" + "[" + getStatusIcon() + "] " + getDescription()
                + " (by: " + by + ")";
    }

    @Override
    public String toString() {
        return super.toString() + " | " + this.by;
    }
}
