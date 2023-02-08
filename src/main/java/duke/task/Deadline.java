package duke.task;

public class Deadline extends Task{
    protected String by;

    public Deadline(int Index, String description, String by) {
        super(Index,description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return super.toString() + "[D]" + "[" + getStatusIcon() + "] " + getDescription() + " (by: " + getBy() + ")";
    }
}
