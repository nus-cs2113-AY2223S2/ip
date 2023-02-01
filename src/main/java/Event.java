public class Event extends Task {
    protected static final String OPEN_CURVE_BRACKET = " (";
    protected static final String CLOSE_CURVE_BRACKET = ")";
    protected static final String EVENT_ICON = "E";
    protected static final String FROM_DESC = "from: ";
    protected static final String TO_DESC = " to: ";

    protected String from;
    protected String to;

    public Event(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return OPEN_SQUARE_BRACKET + EVENT_ICON + CLOSE_SQUARE_BRACKET
                + super.toString() + OPEN_CURVE_BRACKET + FROM_DESC
                + from + TO_DESC + to + CLOSE_CURVE_BRACKET;
    }

}
