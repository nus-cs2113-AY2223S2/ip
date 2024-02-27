package duke.model;

import duke.exception.InvalidCommandException;

/**
 * A class to store Event entity
 */
public class Event extends Task {

    /**
     * Represents the start of the event
     */
    protected String eventStart;

    /**
     * Represents the end of the event
     */
    protected String eventEnd;

    /**
     * A constructor that intializes the start and end of the event, along with the name of the event
     *
     * @param descriptionArray
     * @throws InvalidCommandException
     */
    public Event(String[] descriptionArray) throws InvalidCommandException {
        if (descriptionArray.length < 3) {
            throw new InvalidCommandException("Incomplete description of event!");
        }
        System.out.println("test");
        this.taskName = descriptionArray[0];
        this.eventStart = descriptionArray[1];
        this.eventEnd = descriptionArray[2];
    }

    /**
     * Method to represent Event as a string
     *
     * @return String representation of Event
     */
    @Override
    public String toString() {
        String deadlinePrefix = "[E]";
        String taskString = super.toString();
        String eventPostfix = " (" + "from: " + this.eventStart + " to: " + this.eventEnd + ")";
        return deadlinePrefix + taskString + eventPostfix;
    }
}

