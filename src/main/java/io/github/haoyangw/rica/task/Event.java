package io.github.haoyangw.rica.task;

import io.github.haoyangw.rica.exception.RicaSerializationException;
import io.github.haoyangw.rica.exception.RicaTaskException;

/**
 * Represents an Event Task that involves a start date/time and end date/time
 */
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

    /**
     * Parses a command issued by the user and extracts the deadline description
     *   portion, which is returned to the caller
     *
     * @param command Full command issued by the user
     * @return String Object containing the description of the deadline to be created
     * @throws RicaTaskException If command doesn't contain a description of the
     *   deadline to be created
     */
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

    /**
     * Parses an Event creation command issued by the user and extracts the desired
     *   end date/time, which is returned to the caller.
     *
     * @param command Event creation command issued by the user
     * @return String Object containing the desired end date/time
     * @throws RicaTaskException If an end date/time is not found within the event
     *   creation command
     */
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

    /**
     * Parses an Event creation command issued by the user and extracts the desired
     *   start date/time, which is returned to the caller.
     *
     * @param command Event creation command issued by the user
     * @return String Object containing the start date/time of this Event
     * @throws RicaTaskException If no start date/time is found within Event creation
     *   command
     */
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

    /**
     * Parses an Event creation command and instantiates an Event object with the
     *   desired start date, end date and description.
     *
     * @param command Event creation command issued by the user
     * @return Instance of Event with the user's desired start date, end date and
     *   description
     * @throws RicaTaskException If wrong command was issued for Event creation
     */
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

    /**
     * Parses a String representation of an Event Object and re-creates an instance
     *   of Event with the previously saved state.
     *
     * @param objectData String representation of an Event Object's state variables
     * @return Instance of Event with the previously saved state
     * @throws RicaSerializationException If the serialised Task is not an Event
     *   or not all state variables were saved
     */
    public static Event deserializeObject(String objectData) throws RicaSerializationException {
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

    /**
     * Generates a String representation of the current Event's state variables
     *   to be written to persistent storage later.
     *
     * @return String representation of the current Event instance
     * @see io.github.haoyangw.rica.storage.Serializable
     */
    @Override
    public String serializeObject() {
        String todoData = super.serializeObject();
        return todoData + Task.DATA_STRING_SEPARATOR + this.getStartTime()
                + Task.DATA_STRING_SEPARATOR + this.getEndTime();
    }

    /**
     * Sets this Event Task as done/not done by the user
     *
     * @param isDone Whether this Event is now done
     * @return Instance of Event with isDone state variable set accordingly
     */
    @Override
    public Event setDone(boolean isDone) {
        return new Event(super.getDescription(), isDone, this.getStartTime(),
                this.getEndTime());
    }

    /**
     * Generates a user-friendly String representation of this Event instance
     *   for the user to understand this Event's current state
     *
     * @return String representation of this Event instance's state
     */
    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(),
                this.getStartTime(), this.getEndTime());
    }
}
