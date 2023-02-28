package duke;

public class Event extends Deadline {
    public String from;

    public Event(String description, String from, String to) {
        super(description, to);
        this.from = from;
        this.type = TaskType.EVENT;
    }

    @Override
    public String getLabel() {
        String typeIndicator = "[E]";
        String doneIndicator = "[" + (this.isDone ? "X" : " ") + "]";
        String suffix = "(from: " + this.from + " to: " + this.by + ")";
        return typeIndicator + doneIndicator + " " + this.description + " " + suffix;
    }
}
