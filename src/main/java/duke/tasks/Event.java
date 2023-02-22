package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends ToDo {

    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructor for Events class.
     *
     * @param taskName Task description.
     * @param start    Start time of event.
     * @param end      End time of event.
     */
    public Event(String taskName, LocalDateTime start, LocalDateTime end) {
        super(taskName);
        super.type = "[E]";
        this.start = start;
        this.end = end;
    }

    /**
     * Formats the start time to a more readable format.
     *
     * @return Formatted start time.
     */
    public String formatStart() {
        return this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }

    /**
     * Getter for start field.
     *
     * @return the start time of the event.
     */
    public String getStart() {
        return this.start.toString();
    }

    /**
     * Formats the end time to a more readable format.
     *
     * @return Formatted end time.
     */
    public String formatEnd() {
        return this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }

    /**
     * Getter for end field.
     *
     * @return the end time of the event.
     */
    public String getEnd() {
        return this.end.toString();
    }

    @Override
    public String toString() {
        return checkBoxOutput() + this.taskName + " (from: " + this.formatStart() + " to: " + this.formatEnd() + ")";
    }
}
