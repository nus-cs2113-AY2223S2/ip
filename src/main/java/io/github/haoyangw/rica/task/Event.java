package io.github.haoyangw.rica.task;

import io.github.haoyangw.rica.exception.RicaSerializationException;
import io.github.haoyangw.rica.exception.RicaTaskException;

public class Event extends Todo {
    private static final String COMMAND = "event";
    private static final String MISSING_DESC_ERROR = " Remember to provide a description of the event you're adding y'know!";
    private static final String MISSING_END_TIME_ERROR = " You didn't provide an end date/time! :(";
    private static final String MISSING_START_TIME_ERROR = " You didn't give me a start date/time how do I create an event xD";
    private static final String END_KEYWORD = "/to";
    private static final int NUM_OF_SERIALIZED_DATA = 5;
    private static final String START_KEYWORD = "/from";
    private static final String WRONG_CMD_ERROR = " Hmm I'm being told to add an event with the wrong command. Help xP";
    protected static final String TYPE = "E";
    private final String endTime;
    private final String startTime;

    public Event(String description, String startTime, String endTime) {
        this(description, false, startTime, endTime);
    }

    private Event(String description, boolean isDone, String startTime, String endTime) {
        super(description, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    private static String getDescriptionOf(String command) throws RicaTaskException {
        String[] parameters = command.split(" ");
        StringBuilder descBuilder = new StringBuilder();
        for (int i = 1; i < parameters.length; i += 1) {
            if (parameters[i].equals(Event.START_KEYWORD)) {
                break;
            }
            if (i != 1) {
                descBuilder.append(" ");
            }
            descBuilder.append(parameters[i]);
        }
        String description = descBuilder.toString();
        if (description.isBlank()) {
            throw new RicaTaskException(Event.MISSING_DESC_ERROR);
        }
        return description;
    }

    private String getEndTime() {
        return this.endTime;
    }

    private static String getEndTimeOf(String command) throws RicaTaskException {
        int endDateFirstIndex = command.indexOf(Event.END_KEYWORD)
                + Event.END_KEYWORD.length();
        String endTime = command.substring(endDateFirstIndex).trim();
        if (endTime.isBlank()) {
            throw new RicaTaskException(Event.MISSING_END_TIME_ERROR);
        }
        return endTime;
    }

    private String getStartTime() {
        return this.startTime;
    }

    private static String getStartTimeOf(String command) throws RicaTaskException {
        boolean hasStartTimeKeyword = command.contains(Event.START_KEYWORD);
        if (!hasStartTimeKeyword) {
            throw new RicaTaskException(Event.MISSING_START_TIME_ERROR);
        }
        int startDateFirstIndex = command.indexOf(Event.START_KEYWORD)
                + Event.START_KEYWORD.length();
        boolean hasEndTimeKeyword = command.contains(Event.END_KEYWORD);
        if (!hasEndTimeKeyword) {
            throw new RicaTaskException(Event.MISSING_END_TIME_ERROR);
        }
        int startDateLastIndex = command.indexOf(Event.END_KEYWORD) - 1;
        String startTime = command.substring(startDateFirstIndex, startDateLastIndex).trim();
        if (startTime.isBlank()) {
            throw new RicaTaskException(Event.MISSING_START_TIME_ERROR);
        }
        return startTime;
    }

    @Override
    protected String getType() {
        return Event.TYPE;
    }

    public static Event create(String command) throws RicaTaskException {
        String[] parameters = command.trim().split(" ");
        // Wrong command for adding an event
        if (!parameters[0].equals(Event.COMMAND)) {
            throw new RicaTaskException(Event.WRONG_CMD_ERROR);
        }
        String description = Event.getDescriptionOf(command);
        String startTime = Event.getStartTimeOf(command);
        String endTime = Event.getEndTimeOf(command);
        return new Event(description, startTime, endTime);
    }

    public static Event deserializeObject(String objectData) {
        String[] variables = objectData.split(Task.DATA_STRING_SEPARATOR_REGEX);
        if (variables.length < Event.NUM_OF_SERIALIZED_DATA) {
            throw new RicaSerializationException(Task.INCOMPLETE_SERIALIZED_OBJECT_STRING);
        }
        if (!variables[0].equals(Event.TYPE)) {
            throw new RicaSerializationException(Task.WRONG_SERIALIZED_OBJECT_TYPE);
        }
        String description = variables[1];
        boolean isDone = Boolean.parseBoolean(variables[2]);
        String startTime = variables[3];
        String endTime = variables[4];
        return new Event(description, isDone, startTime, endTime);
    }

    @Override
    public String serializeObject() {
        String todoData = super.serializeObject();
        return todoData + Task.DATA_STRING_SEPARATOR + this.getStartTime()
                + Task.DATA_STRING_SEPARATOR + this.getEndTime();
    }

    @Override
    public Event setDone(boolean isDone) {
        return new Event(super.getDescription(), isDone, this.getStartTime(),
                this.getEndTime());
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(),
                this.getStartTime(), this.getEndTime());
    }
}
