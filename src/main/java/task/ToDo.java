package task;

import parser.InvalidCommandException;

public class ToDo extends Task {
    public ToDo(String description) throws EmptyDescriptionException {
        super(description);
    }
    public ToDo() {
        super();
    }
    @Override
    public String toString() {
        return String.format("[T][%c] %s", getStatusIcon(), description);
    }
    @Override
    public void parseArgument(String arguments) throws InvalidCommandException,EmptyDescriptionException {
        setDescription(arguments);
    }
}