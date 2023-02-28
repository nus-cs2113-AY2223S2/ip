package duke.tasks;
import java.time.format.DateTimeParseException;
import duke.exception.InvalidDateTimeFormatException;
import duke.parser.DateTimeParser;


public class Event extends Task {
    protected String startTime;
    protected String endTime;


    public Event(String description, String startTime , String endTime) throws InvalidDateTimeFormatException{
        super(description,TaskType.EVENT);
        try {
            this.startTime = new DateTimeParser().parse(startTime);
            this.endTime = new DateTimeParser().parse(endTime);
        } catch(DateTimeParseException exception){
            throw new InvalidDateTimeFormatException();
        }
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(), startTime, endTime);
    }

    @Override
    public String saveText() {
        return super.saveText() + " | " + startTime + " | " + endTime;
    }
}