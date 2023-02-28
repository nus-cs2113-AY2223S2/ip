package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import parser.InvalidCommandException;
import serialiser.SerialiseException;

public class Deadline extends ToDo {
    protected String endDate;
    protected LocalDateTime endDateTimeObject = null;
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
        endDateTimeObject = stringToDateTime(newEndDate);
        endDate = newEndDate;
    }
    protected LocalDateTime stringToDateTime(String stringText) {
        String[] FORMATS = {"dd/MM/yyyy HHmm",
                            "d/MM/yyyy HHmm",
                            "d/M/yyyy HHmm"};

        for (String format: FORMATS) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                return LocalDateTime.parse(stringText, formatter);
            } catch (Exception e) {
                // Just loop
            }
        }
        return null;
    }
    public String getEndDate() {
        if (endDateTimeObject!=null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
            return endDateTimeObject.format(formatter).toString();
        }
        return endDate;
    }
    @Override
    public String toString() {
        return String.format("[D][%c] %s (by: %s)", getStatusIcon(), description, getEndDate());
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
            setEndDate(arguments.substring(by + 4).trim());
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