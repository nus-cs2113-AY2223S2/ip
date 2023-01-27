public class Deadline extends Task {
    protected String by;

    Deadline(String description, String by) {
        super(description);
        this.type = "D";
        this.by = by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.by + ")";
    }
}
