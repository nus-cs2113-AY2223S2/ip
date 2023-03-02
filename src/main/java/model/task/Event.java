package model.task;

/**
 * An event model
 */
public class Event extends Task {
    protected String from;
    protected String to;

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public Event(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getDescriptionText() {
        String symbol = super.isDone() ? "X" : " ";
        return String.format("[E][%s] %s (from: %s to: %s)",
                symbol,
                super.getTaskName(),
                this.from,
                this.to
        );
    }
}
