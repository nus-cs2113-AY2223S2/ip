package duke.addable;
import duke.exception.ArgumentBlankException;

public class ToDo extends Task {

    protected final String commandString = "todo";

    public ToDo(String description, boolean isDone) throws ArgumentBlankException {
        super(description, isDone);
    }

    @Override
    public String getCommandString() {
        return commandString;
    }
    @Override
    public String getLetter() {
        return "T";
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String[] getExtraArguments() {
        String[] extraArguments = {};
        return extraArguments;
    }
}