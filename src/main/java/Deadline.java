public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getTaskIcon() {
        return "D";
    }

    public String getDeadlineBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }
}
