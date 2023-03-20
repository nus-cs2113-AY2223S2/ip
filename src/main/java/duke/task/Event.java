package duke.task;

import duke.DukeException;

public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String description, String from, String to) throws DukeException {
        super(description);
        if (from.isBlank() || to.isBlank()) {
            throw new DukeException("Event needs to have both /from and /to flags");
        }
        this.from = from;
        this.to = to;
    }

    public String getStatus() {
        return ("E [" + (isDone? "u\2718" : " ") + "]");
    }
    public String getFrom() {
        return ("from: " + from);
    }
    public String getTo() {
        return ("to: " + to);
    }
    @Override
    public String toString() {
        return this.getStatus() + " " + description + " " + getFrom() + " " + getTo();
    }
}
