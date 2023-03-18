package task;

public class Event extends Task {

    protected String timeOfEvent;
    public Event(String name, String timeOfEvent) {
        super(name);
        this.timeOfEvent = timeOfEvent;
    }

    public String toString() {
        timeOfEvent = timeOfEvent.replace("/", "");
        return timeOfEvent;
    }

    @Override
    public String getTaskType() {
        return "E";
    }
}
