package dev.joulev.archduke.tasks;

import java.time.LocalDateTime;

import dev.joulev.archduke.datetime.DateTime;
import dev.joulev.archduke.exceptions.ArchdukeException;
import dev.joulev.archduke.exceptions.UserInputException;
import dev.joulev.archduke.exceptions.UserInputException.UserInputExceptionCode;
import dev.joulev.archduke.storage.SavedTask;

/**
 * This class represents an event task.
 */
public class Event extends Task {
    /** The start time of the event */
    private LocalDateTime from;
    /** The end time of the event */
    private LocalDateTime to;

    /**
     * Constructs a new {@link Event} object.
     * 
     * @param description The description of the task.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     * @throws ArchdukeException If any of the parameters are invalid (e.g. empty or
     *                           {@code null}).
     */
    public Event(String description, String from, String to) throws ArchdukeException {
        super(description);
        setFrom(from);
        setTo(to);
    }

    /**
     * Constructs a new {@link Event} object.
     * 
     * @param description The description of the task.
     * @param isCompleted Whether the task is completed.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     * @throws ArchdukeException If any of the parameters are invalid (e.g. empty or
     *                           {@code null}).
     */
    public Event(String description, boolean isCompleted, String from, String to)
            throws ArchdukeException {
        super(description, isCompleted);
        setFrom(from);
        setTo(to);
    }

    public void setFrom(String from) throws ArchdukeException {
        if (from == null || from.isBlank()) {
            throw new UserInputException(UserInputExceptionCode.TODO_FROM_IS_EMPTY);
        }
        this.from = DateTime.parse(from);
    }

    public void setTo(String to) throws ArchdukeException {
        if (to == null || to.isBlank()) {
            throw new UserInputException(UserInputExceptionCode.TODO_TO_IS_EMPTY);
        }
        this.to = DateTime.parse(to);
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    @Override
    public String toString() {
        return String.format("E %s (from: %s; to: %s)", super.toString(),
                DateTime.display(getFrom()), DateTime.display(getTo()));
    }

    public SavedTask toSavedTask() {
        return new SavedTask(SavedTask.EVENT_IDENTIFIER, getDescription(), isCompleted(),
                DateTime.formatForSave(getFrom()), DateTime.formatForSave(getTo()), null);
    }
}
