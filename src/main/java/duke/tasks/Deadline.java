package duke.tasks;
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
    public String encode() {
        return String.format("%s ### %s ### %s", "deadline", super.encode(), this.by);
    }

    public String getType() {
        return "deadline";
    }
}
