package duke.tasks;
import java.time.format.DateTimeParseException;

import duke.exception.InvalidDateTimeFormatException;
import duke.parser.DateTimeParser;

public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) throws InvalidDateTimeFormatException{
        super(description, TaskType.DEADLINE);
        try{
            this.by = new DateTimeParser().parse(by);
        }catch (DateTimeParseException exception){
            throw new InvalidDateTimeFormatException();
        }

    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), by);
    }

    @Override
    public String saveText() {
        return super.saveText() + " | " + this.by;
    }
}




