package duke.model;

import duke.exception.InvalidCommandException;

public class Event extends Task {
    protected String eventStart;
    protected String eventEnd;

    public static String[] parseCommand (String command) throws InvalidCommandException {
        String[] descriptionArray = new String[3];
        String[] descriptionAndEventInfo = command.split("event")[1].split("/");
        if (descriptionAndEventInfo.length < 3) {
            throw new InvalidCommandException("Incomplete event description!");
        }
        for (int i = 0; i < 3; i++){
            descriptionArray[i] = descriptionAndEventInfo[i].trim();
        }
        return descriptionArray;
    }
    public Event(String[] descriptionArray) {
        super(descriptionArray);
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

