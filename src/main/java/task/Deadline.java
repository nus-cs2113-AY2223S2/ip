package task;

import parser.InvalidCommandException;

public class Deadline extends ToDo {
    protected String endDate;

	public Deadline(String description, String endDate) throws EmptyDescriptionException {
        // TODO consider when endDate is null
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
        int by = arguments.indexOf("/by");
        // Check if expression exist and if it is surrounded by a space
        if (by <= 0 || arguments.charAt(by-1)!=' ' || arguments.charAt(by+3)!=' ') {
            throw new InvalidCommandException("Command could not be understood", new IllegalArgumentException());
        }
        setDescription(arguments.substring(0,by - 1));
        setEndDate(arguments.substring(by + 4));
    }
}