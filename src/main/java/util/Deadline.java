package util;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toStringForSave() {
        String doneStatus = super.isDone() ? "1" : "0";
        return String.format("D | %s | %s | %s", doneStatus, super.getDescription(), by);
    }
}