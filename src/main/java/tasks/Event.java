package tasks;

import java.time.LocalDateTime;

public class Event extends Task{
    private String from;
    private String to;
    private String type = "E";

    public Event(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "[" + type + "] [" + getStatus() + "] " + getTask() + " (From: " + from + " To: " + to + ")";
    }
}
