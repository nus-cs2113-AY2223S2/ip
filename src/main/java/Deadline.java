public class Deadline extends Task {
    protected String dueBy;

    public Deadline(String description, String dueBy) {
        super(description);
        this.dueBy = dueBy;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueBy + ")";
    }
}
