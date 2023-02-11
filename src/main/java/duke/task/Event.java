package duke.task;

public class Event extends Task {
    private String from;
    private String to;

    public Event(String desciption, String from, String to) {
        super(desciption);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    public String getStartTime() {
        return from;
    }

    public String getEndTime() {
        return to;
    }
}
