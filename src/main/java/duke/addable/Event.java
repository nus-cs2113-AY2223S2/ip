package duke.addable;
import duke.exception.ArgumentBlankException;

public class Event extends Task {

    protected String startTime;
    protected String endTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    protected final String commandString = "event";

    @Override
    public String getCommandString() {
        return commandString;
    }

    @Override
    public String getLetter() {
        return "E";
    }

    public Event(String description, String startTime, String endTime, boolean isDone) throws ArgumentBlankException {
        super(description, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String[] getExtraArguments() {
        String[] extraArguments = {this.startTime, this.endTime};
        return extraArguments;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to:" + endTime + ")";
    }
}