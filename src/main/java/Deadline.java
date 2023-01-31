public class Deadline extends Task {

    public static final String DEADLINE_ICON = "[D]";
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return DEADLINE_ICON + super.toString() + "(by: " + by + ")";
    }
}
