package duke.task;

/**
 * The <code>Event</code> object represents the event that the user adds.
 * It contains a start and end date/time.
 */
public class Event extends Task {

    /**
     * The class constructor that sets the description of the event.
     *
     * @param description The description of the event.
     */
    public Event(String description) {
        super(description);
        this.taskType = TaskType.EVENT;
    }

    /**
     * Formats the event into a specific String.
     *
     * @return A formatted String representing the event.
     */
    @Override
    public String toString() {
        String[] descriptionParts = this.description.split("/from");
        String[] descriptionFromTo = descriptionParts[1].split("/to");
        String descriptionDetails = descriptionParts[0];
        String descriptionFrom = descriptionFromTo[0];
        String descriptionTo = descriptionFromTo[1];

        return "[" + this.getType() + "][" + this.getStatusIcon() + "] " +
                descriptionDetails + "(from:" + descriptionFrom + "To:" + descriptionTo + ")";
    }
}
