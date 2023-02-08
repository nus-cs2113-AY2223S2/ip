package duke.addable;
import duke.exception.ArgumentBlankException;
public class Deadline extends Task {

    protected String by;
    protected final String commandString = "deadline";
    public Deadline(String description, String by) throws ArgumentBlankException {
        super(description);
        if (by.isBlank()) {
            throw new ArgumentBlankException("deadline", "by");
        }
        this.by = by;
    }

    @Override
    public String getCommandString() {
        return commandString;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}