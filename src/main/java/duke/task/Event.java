package duke.task;

public class Event extends Task {

    protected String eventFrom;
    protected String eventTo;

    public Event(String description, String eventFrom, String eventTo) {
        super(description);
        this.eventFrom = eventFrom;
        this.eventTo = eventTo;
        this.taskString = saveTaskString();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + eventFrom + " to: " + eventTo + ")";
    }

    @Override
    public String saveTaskString() {
        String saveString = new String();
        saveString += "E" + COMMA + isDone + COMMA + description + COMMA + eventFrom + COMMA + eventTo;
        return saveString;
    }
}
