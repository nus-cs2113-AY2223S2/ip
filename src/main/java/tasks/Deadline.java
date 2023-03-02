package tasks;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.type= "deadline";
        this.by = by;
    }

    public String getDetails() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D][" + getComplete() + "] " + getTask() + " (By: " + by + ")";
    }
}
