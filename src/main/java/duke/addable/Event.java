package duke.addable;
import duke.exception.ArgumentBlankException;
import java.time.LocalDate;
public class Event extends Task {

    protected LocalDate startTime;
    protected LocalDate endTime;

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
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
        this.startTime = LocalDate.parse(startTime.strip());
        this.endTime = LocalDate.parse(endTime.strip());
    }

    public String[] getExtraArguments() {
        String[] extraArguments = {this.startTime.toString(), this.endTime.toString()};
        return extraArguments;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getDateString(startTime) + " to: " + getDateString(endTime) + ")";
    }
}