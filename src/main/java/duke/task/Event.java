package duke.task;

/**
 * Represents a event Task
 */
public class Event extends Task {
    protected String start;
    protected String end;

    /**
     * Constructor for an event task
     *
     * @param description the description of the event task
     * @param taskType the type of task
     * @param start the start timing of the event task
     * @param end the end timing of the event task
     */
    public Event(String description, String taskType, String start, String end) {
        super(description, taskType);
        this.start = start;
        this.end = end;
    }

    /**
     * Output the event task and its details
     *
     * @return the type, status, description and the period of the event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    /**
     * Represents the details of the event task to be saved on the text file
     *
     * @return the type, status, description and period of the event task
     */
    @Override
    public String textToSave() {
        return "E | " + (super.isDone ? 1 : 0) + " | " + super.description + " | " + start + " | " + end;
    }
}