package duke.addable;
import duke.exception.ArgumentBlankException;
public class Deadline extends Task {

    protected String by;
    protected final String commandString = "deadline";
    public Deadline(String description, String by, boolean isDone) throws ArgumentBlankException {
        super(description, isDone);
        if (by.isBlank()) {
            throw new ArgumentBlankException("deadline", "by");
        }
        this.by = by;
    }

    @Override
    public String getCommandString() {
        return commandString;
    }

    public String[] getExtraArguments() {
        String[] extraArguments = {this.by};
        return extraArguments;
    }

    @Override
    public String getLetter() {
        return "D";
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}