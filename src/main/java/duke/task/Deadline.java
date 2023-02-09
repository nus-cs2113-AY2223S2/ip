package duke.task;

public class Deadline extends Task {
    protected static final String OPEN_CURVE_BRACKET = " (";
    protected static final String CLOSE_CURVE_BRACKET = ")";
    protected static final String DEADLINE_ICON = "D";
    protected static final String BY_DESC = "by: ";

    protected String by;

    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
    }

    @Override
    public String toString() {
        return OPEN_SQUARE_BRACKET + DEADLINE_ICON + CLOSE_SQUARE_BRACKET
                + super.toString() + OPEN_CURVE_BRACKET + BY_DESC + by
                + CLOSE_CURVE_BRACKET;
    }
}
