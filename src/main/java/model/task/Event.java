package model.task;
/**
 * Represents an event with a start and end time
 * 
 * @author Wen Jun
 * @version 1.00
 */
public class Event extends Task {
    protected String from;
    protected String to;

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
