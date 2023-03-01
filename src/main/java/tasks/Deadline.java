package tasks;

import tasks.Task;

public class Deadline extends Task {
    public String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "] " + super.description.toString() + "(by:" + by + ")";
    }
}
