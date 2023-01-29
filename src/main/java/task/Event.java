package task;

import parser.InvalidCommandException;

public class Event extends Deadline {
    protected String startDate;
    public Event(String description, String startDate, String endDate) throws EmptyDescriptionException {
        super(description,endDate);
        setStartDate(startDate);
    }
    public Event() {
        super();
        startDate = null;
    }
    @Override
    public String toString() {
        return String.format("[E][%c] %s (from: %s to: %s)", getStatusIcon(), description, startDate, endDate);
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String newStartDate) {
        startDate = newStartDate;
    }
    @Override
    public void parseArgument(String arguments) throws InvalidCommandException, EmptyDescriptionException {
        int from = arguments.indexOf("/from");
        int to = arguments.indexOf("/to");
        if (from == -1 || arguments.charAt(from-1)!=' ' || arguments.charAt(from+5)!=' ') {
            throw new InvalidCommandException("Command at /from is invalid", new IllegalArgumentException());
        }
        if (to == -1 || arguments.charAt(to-1)!=' ' || arguments.charAt(to+3)!=' ') {
            throw new InvalidCommandException("Command at /to is invalid", new IllegalArgumentException());
        }
        setDescription(arguments.substring(0,from - 1));
        setEndDate(arguments.substring(to + 4));
        setStartDate(arguments.substring(from + 6, to -1));
    }
}
