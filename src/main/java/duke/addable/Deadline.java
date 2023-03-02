package duke.addable;
import duke.exception.ArgumentBlankException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;
    protected final String commandString = "deadline";
    public Deadline(String description, String by, boolean isDone) throws ArgumentBlankException {
        super(description, isDone);
        this.by = LocalDate.parse(by.strip());
    }

    @Override
    public String getCommandString() {
        return commandString;
    }

    public String[] getExtraArguments() {
        String[] extraArguments = {this.by.toString()};
        return extraArguments;
    }

    @Override
    public String getLetter() {
        return "D";
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDateString(by) + ")";
    }
}