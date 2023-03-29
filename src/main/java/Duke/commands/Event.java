package Duke.commands;

import Duke.commands.Task;

public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String description, String start, String end){
        super(description);
        this.start = start;
        this.end = end;
    }

    public String getStartTime() {
        return start;
    }

    public String getDueTime() {
        return end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start +" to: " + end + ")";
    }

}
