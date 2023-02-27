package alltasks;

public class Event extends Task{

    public Event(String descriptive) {
        super(descriptive);
    }

    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "]" + " " + description;
    }
}
