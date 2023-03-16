package tasks;

public class Deadline extends Task {
    protected String by;
    private String type = "D";

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "[" + type + "] [" + getStatus() + "] " + getTask() + " (By: " + by + ")";
    }
}