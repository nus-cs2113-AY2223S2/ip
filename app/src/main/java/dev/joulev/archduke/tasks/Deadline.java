package dev.joulev.archduke.tasks;

import dev.joulev.archduke.exceptions.ArchdukeException;
import dev.joulev.archduke.exceptions.UserInputException;
import dev.joulev.archduke.exceptions.UserInputException.UserInputExceptionCode;
import dev.joulev.archduke.storage.SavedTask;

/**
 * This class represents a deadline task.
 */
public class Deadline extends Task {
    /** The deadline of the task */
    private String by;

    /**
     * Constructs a new {@link Deadline} object.
     * 
     * @param description The description of the task.
     * @param by          The deadline of the task.
     * @throws ArchdukeException If any of the parameters are invalid (e.g. empty or
     *                           {@code null}).
     */
    public Deadline(String description, String by) throws ArchdukeException {
        super(description);
        setBy(by);
    }

    /**
     * Constructs a new {@link Deadline} object.
     * 
     * @param description The description of the task.
     * @param isCompleted Whether the task is completed.
     * @param by          The deadline of the task.
     * @throws ArchdukeException If any of the parameters are invalid (e.g. empty or
     *                           {@code null}).
     */
    public Deadline(String description, boolean isCompleted, String by) throws ArchdukeException {
        super(description, isCompleted);
        setBy(by);
    }

    public void setBy(String by) throws ArchdukeException {
        if (by == null || by.isBlank()) {
            throw new UserInputException(UserInputExceptionCode.DEADLINE_BY_IS_EMPTY);
        }
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return String.format("D %s (by: %s)", super.toString(), getBy());
    }

    public SavedTask toSavedTask() {
        return new SavedTask(SavedTask.DEADLINE_IDENTIFIER, getDescription(), isCompleted(), null,
                null, getBy());
    }
}
