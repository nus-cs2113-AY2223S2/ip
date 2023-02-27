package chronos.tasktype;

import chronos.savehandler.Save;

/**
 * Represents an Event type task, a subclass of Task
 */
//more inheritance
public class Event extends Task {

    private String start; //start time of an event type task
    private String end; //end time of an event type task

    /**
     * Creates a new {@code Event} task with the specified description and start/end times.
     *
     * @param description a description of the event
     * @param start the start time of the event, in string format
     * @param end the end time of the event, in string format
     * @throws IllegalArgumentException if either the start or end time is null
     */
    public Event(String description, String start, String end) {
        super(description);
        if (start == null || end == null) {
            throw new IllegalArgumentException("Please provide BOTH start and end times.");
        }
        this.end = end;
        this.start = start;
    }

    /**
     * Creates a new {@code Event} task with the specified description, start/end times, and completion status.
     *
     * @param description a description of the event
     * @param start the start time of the event, in string format
     * @param end the end time of the event, in string format
     * @param isDone the completion status of the task
     * @throws IllegalArgumentException if either the start or end time is null
     */
    public Event(String description, String start, String end, boolean isDone) {
        super(isDone, description);
        if (start == null || end == null) {
            throw new IllegalArgumentException("Please provide BOTH start and end times.");
        }
        this.end = end;
        this.start = start;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    /**
     * Returns a string representation of this {@code Event} task, in the format "[E] [task description] (start: [start time] end: [end time])".
     *
     * @return a string representation of this event task
     */
    @Override
    public String toString() {
        return String.format("[E] %s (start: %s end: %s)", super.toString(), getStart(), getEnd());
    }
    /**
     * Creates a {@code Save} object representing this {@code Event} task.
     *
     * @param taskType the type of task (i.e. "E" for Event)
     * @return a Save object representing this Event task
     */
    @Override
    public Save toSave(String taskType) {
        return new Save(taskType, isDone(), getDescription(), "", start, end);
    }

}
