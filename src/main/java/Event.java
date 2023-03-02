public class Event extends Task {
    private final String from;
    private final String to;

    /**
     * @param description description of the task
     * @param from the start day of the task
     * @param to the end day of the task
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * @return convert to formatted string
     *
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + from + " to:" + to + ")";
    }
}
