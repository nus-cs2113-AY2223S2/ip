package duke;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] "+ this.description + " (by:" + by + ")";
    }

    @Override
    public String saveTask() {
        return ("D" + "//" + checkCompletion() + "//" + getDescription() + "//" + this.by + "\n");
    }
}
