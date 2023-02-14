package duke.model;

import duke.exception.InvalidCommandException;

public class Event extends Task {
    protected String eventStart;
    protected String eventEnd;

    public Event(String[] descriptionArray) throws InvalidCommandException {
        if (descriptionArray.length < 3) {
            throw new InvalidCommandException("Incomplete description of event!");
        }
        System.out.println("test");
        this.taskName = descriptionArray[0];
        this.eventStart = descriptionArray[1];
        this.eventEnd = descriptionArray[2];
    }

    @Override
    public String toString() {
        String deadlinePrefix = "[E]";
        String taskString = super.toString();
        String eventPostfix = " (" + "from: " + this.eventStart + " to: " + this.eventEnd + ")";
        return deadlinePrefix + taskString + eventPostfix;
    }
}

