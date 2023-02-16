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
        saveString += "E" + COMMA_TASK_SEPARATOR + isDone + COMMA_TASK_SEPARATOR + description + COMMA_TASK_SEPARATOR
                + eventFrom + COMMA_TASK_SEPARATOR + eventTo;
        return saveString;
    }
}
