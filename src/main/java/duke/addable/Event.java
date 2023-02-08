package duke.addable;
import duke.exception.ArgumentBlankException;

public class Event extends Task {

    protected String startTime;
    protected String endTime;
    protected final String commandString = "event";

    @Override
    public String getCommandString() {
        return commandString;
    }

    public Event(String description, String startTime, String endTime) throws ArgumentBlankException {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to:" + endTime + ")";
    }
}