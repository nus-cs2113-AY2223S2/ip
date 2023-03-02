package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task to be processed by <code>Duke</code>.
 * It has three child classes: <code>Todo</code>, <code>Deadline</code> and <code>Event</code>.
 */
public abstract class Task {

    public static final String dateTimeParseFormat = "yyyy/MM/dd' 'HH:mm";
    public static final String dateTimePrintFormat = "MMM dd yyyy hh:mm a";
    public static final DateTimeFormatter parseFormatter = DateTimeFormatter.ofPattern(dateTimeParseFormat);
    public static final DateTimeFormatter printFormatter = DateTimeFormatter.ofPattern(dateTimePrintFormat);
    
    protected String description;
    protected boolean isDone;
    protected char type;

    /**
     * Constructor of <code>Task</code>. It constructs a <code>Task</code>
     * with input description and type of the task.
     * @param description Task description, e.g. "Return books".
     * @param type <code>Todo</code>('T'), <code>Deadline</code>('D') or <code>Event</code>('E').
     */
    public Task(String description, char type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Set task description.
     * @param description Task description, e.g. "Return books".
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Return description of a task.
     * @return Task description, e.g. "Return books".
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the mark whether the task is done.
     * @param isDone <code>true</code> for done and <code>false</code> for not done yet.
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Return the mark whether the task is done.
     * @return <code>true</code> for done and <code>false</code> for not done yet.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Return the status icon for whether the task it done.
     * @return " " if the task is done and "X" if the task is done yet.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Return the type of the task.
     * @return 'T' for <code>Todo</code> task, 'D' for <code>Deadline</code> task and 'E' for <code>Event</code> task.
     */
    public char getTypeIcon() {
        return type;
    }

    /**
     * Mark the task as done or not done.
     * @param done <code>true</code> to mark the task as done and <code>false</code> for not done yet.
     */
    public void mark(boolean done) {
        isDone = done;
    }

    /**
     * Get the start time and end time of the task if they exist.
     * @return "" for <code>Todo</code>, "<code>by</code>" for <code>Deadline</code> and "<code>from</code>- <code>to</code>" for <code>Event</code>.
     */
    public abstract String getTimeBound();

    /**
     * Returns whether input of the time(s) of the task is represented in format corresponding to the 
     * required time format that can be handled by <code>Duke</code>.
     * @return <code>true</code> if it is written in format "yyyy/MM/dd HH:mm" and <code>false</code> if it is not.
     */
    public abstract boolean haveValidDate();

    /**
     * Returns the end time of the task. Note that input format of the time(s) of the task must be handleable by <code>Duke</code>.
     * @return End time of the task in <code>LocalDateTime</code> type.
     */
    public abstract LocalDateTime getEndTime();

    @Override
    public String toString() {
        return ("[" + getTypeIcon() + "] " + "[" + getStatusIcon() + "] " + getDescription());
    }
}