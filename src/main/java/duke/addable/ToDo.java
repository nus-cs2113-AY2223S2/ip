package duke.addable;
import duke.exception.ArgumentBlankException;

public class ToDo extends Task {

    protected final String commandString = "todo";

    public ToDo(String description) throws ArgumentBlankException {
        super(description);
    }

    @Override
    public String getCommandString() {
        return commandString;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}