package duke.command;

import duke.task.Task;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String taskDescription, String from, String to) {
        super(taskDescription);
        this.from = from;
        this.to = to;
        setCommand("event " + taskDescription + " /from " + from + " /to " + to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
