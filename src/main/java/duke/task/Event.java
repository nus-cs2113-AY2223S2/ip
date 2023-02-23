package duke.task;

public class Event extends Task {

    protected final String taskName;
    protected String eventDetailsPartOne;
    protected String eventDetailsPartTwo;

    public Event(String description, String eventDetailsPartOne, String eventDetailsPartTwo) {
        super(description);
        this.taskName = description;
        this.eventDetailsPartOne = eventDetailsPartOne;
        this.eventDetailsPartTwo = eventDetailsPartTwo;
    }

    /**
     * returns the task stored as a string format to be
     * saved in the save file
     *
     * @return returns the task in the format of user command
     */
    public String returnCommand() {
        return super.completed() + "event " + taskName + " /from " + eventDetailsPartOne + " /to " + eventDetailsPartTwo;
    }

    public String toString() {
        return "[E]" + super.toString() + " (from: " + eventDetailsPartOne + " to: " + eventDetailsPartTwo + ")";
    }
}
