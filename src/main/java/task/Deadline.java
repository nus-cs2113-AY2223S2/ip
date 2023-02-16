package task;

import parser.InvalidCommandException;
import serialiser.SerialiseException;

public class Deadline extends ToDo {
    protected String endDate;
    private static final String DEADLINE_MESSAGE = "D | %d | %s | %s";
	public Deadline(String description, String endDate) throws EmptyDescriptionException {
        super(description);
        setEndDate(endDate);
    }
    public Deadline() {
        super();
        endDate = null;
    }
    public void setEndDate(String newEndDate) {
        endDate = newEndDate;
    }
    public String getEndDate() {
        return endDate;
    }
    @Override
    public String toString() {
        return String.format("[D][%c] %s (by: %s)", getStatusIcon(), description, endDate);
    }
    @Override
    public void parseArgument(String arguments) throws InvalidCommandException, EmptyDescriptionException {
        try {
            int by = arguments.indexOf("/by");
            // Check if expression exist and if it is surrounded by a space
            if (by <= 0 || arguments.charAt(by-1)!=' ' || arguments.charAt(by+3)!=' ') {
                throw new InvalidCommandException("Command could not be understood", new IllegalArgumentException());
            }
            setDescription(arguments.substring(0,by - 1));
            setEndDate(arguments.substring(by + 4));
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("Command at /by is invalid", new IllegalArgumentException());
        }
    }
    @Override
    public String[] fromString(String taskString) throws SerialiseException{
        try {
            String[] arugments = super.fromString(taskString);
            setEndDate(arugments[3]);
            return arugments;
        } catch (SerialiseException e) {
            throw e;
        }
    }
    @Override
    public String toStorageString() {
        return String.format(DEADLINE_MESSAGE, isMark()?1:0 , description, endDate);
    }
}