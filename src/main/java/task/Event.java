package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import parser.InvalidCommandException;
import serialiser.SerialiseException;

public class Event extends Deadline {
    protected String startDate;
    protected LocalDateTime startDateTimeObject = null;
    private static final String EVENT_MESSAGE = "E | %d | %s | %s | %s";
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
        return String.format("[E][%c] %s (from: %s to: %s)", getStatusIcon(), description, getStartDate(), getEndDate());
    }
    public String getStartDate() {
        if (startDateTimeObject!=null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
            return startDateTimeObject.format(formatter).toString();
        }
        return startDate;
    }
    public void setStartDate(String newStartDate) {
        startDateTimeObject = stringToDateTime(newStartDate);
        startDate = newStartDate;
    }
    @Override
    public void parseArgument(String arguments) throws InvalidCommandException, EmptyDescriptionException {
        int fromExist = arguments.indexOf("/from");
        int toExist = arguments.indexOf("/to");

        boolean charBeforeFromIsSpace = arguments.charAt(fromExist-1) != ' ';
        boolean noKeywordAfterFrom = arguments.charAt(fromExist+5) != ' ';
        if (fromExist == -1 || charBeforeFromIsSpace || noKeywordAfterFrom) {
            throw new InvalidCommandException("Command at /from is invalid", new IllegalArgumentException());
        }

        boolean charBeforeToIsSpace = arguments.charAt(toExist-1)!=' ';
        boolean noKeywordAfterTo = arguments.charAt(toExist+3)!=' ';
        if (toExist == -1 || charBeforeToIsSpace || noKeywordAfterTo) {
            throw new InvalidCommandException("Command at /to is invalid", new IllegalArgumentException());
        }
        setDescription(arguments.substring(0,fromExist - 1));
        setEndDate(arguments.substring(toExist + 4));
        setStartDate(arguments.substring(fromExist + 6, toExist -1));
    }
    @Override
    public String[] fromString(String taskString) throws SerialiseException{
        try {
            String[] arugments = super.fromString(taskString);
            setStartDate(arugments[4]);
            return arugments;
        } catch (SerialiseException e) {
            throw e;
        }
    }
    @Override
    public String toStorageString() {
        return String.format(EVENT_MESSAGE, isMark()?1:0 , description, startDate, endDate);
    }
}
