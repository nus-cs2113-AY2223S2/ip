package duke.task;

public class Event extends Task{
    protected String from;
    protected String to;

    /**
     * Creates an event item. On top of the description, type and completion status
     * that are set by creating a task item, the starting and ending timings are
     * also set based on the inputs.
     * @param description String of the description of be stored in task items.
     * @param type String of the type of the task item.
     * @param from String of the starting time of the event item.
     * @param to String of the ending time of the event item.
     */
    public Event(String description, String type, String from, String to) {
        super(description, type);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the starting and ending time for event items on top
     * of printing the description and status of task items.
     * @return String to print.
     */
    @Override
    public String toString() {
        return super.toString() + " from: " + this.from + " to: " + this.to;
    }
}