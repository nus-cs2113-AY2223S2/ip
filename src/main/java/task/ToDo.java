package task;

import parser.InvalidCommandException;
import serialiser.SerialiseException;

public class ToDo extends Task {
    private static final String TODO_MESSAGE = "T | %d | %s";
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
    @Override
    public String[] fromString(String taskString) throws SerialiseException {
        try {
            String[] arguments = taskString.split("[ ][|][ ]");
            setDescription(arguments[2]);
            if (arguments[1].equals("1")){
                setMark(true);
            }
            return arguments;
        } catch (EmptyDescriptionException | TaskMarkException e) {
            throw new SerialiseException("Unable to serialise a ToDo item", e);
        }
    }
    @Override
    public String toStorageString() {
        return String.format(TODO_MESSAGE, isMark()?1:0 , description);
    }
}