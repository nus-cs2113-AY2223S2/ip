package tusky.tasks;

import tusky.tasks.Task;
import tusky.tasks.TaskType;

public class Event extends Task {
    protected TaskType taskType = TaskType.EVENT;

    protected String from; // datetime as a string
    protected String to; // datetime as a string
    public Event(String description, String from, String to) throws EmptyDescriptionException {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String getTaskSymbol() {
        // E for tusky.tasks.Event
        return "E";
    }

    @Override
    public String toString(){
        return String.format("%s (from: %s to: %s)", description, from, to);
    }
}
