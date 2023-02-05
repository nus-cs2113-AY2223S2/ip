package dev.joulev.archduke.tasks;

import dev.joulev.archduke.exceptions.ArchdukeException;
import dev.joulev.archduke.exceptions.UserInputException;
import dev.joulev.archduke.exceptions.UserInputException.UserInputExceptionCode;
import dev.joulev.archduke.storage.SavedTask;

/**
 * An abstract that serves as the base class for all tasks.
 */
public abstract class Task {
    /** The description of the task */
    private String description;
    /** Whether the task is completed or not */
    private boolean isCompleted;

    /**
     * Constructs a new {@link Task} object, with {@code isCompleted} set to
     * {@code false}
     * 
     * @param description The description of the task.
     * @throws ArchdukeException If the description is empty or {@code null}.
     */
    public Task(String description) throws ArchdukeException {
        setDescription(description);
        this.isCompleted = false;
    }

    /**
     * Constructs a new {@link Task} object.
     * 
     * @param description The description of the task.
     * @param isCompleted Whether the task is completed.
     * @throws ArchdukeException If the description is empty or {@code null}.
     */
    public Task(String description, boolean isCompleted) throws ArchdukeException {
        setDescription(description);
        this.isCompleted = isCompleted;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns the status icon of the task for display. Icon taken from
     * {@link https://github.com/sindresorhus/figures/blob/main/index.js}
     */
    public char getStatusIcon() {
        return isCompleted ? '■' : '□';
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setDescription(String description) throws ArchdukeException {
        if (description == null || description.isBlank()) {
            throw new UserInputException(UserInputExceptionCode.TASK_DESCRIPTION_IS_EMPTY);
        }
        this.description = description;
    }

    public void toggleCompleted() {
        this.isCompleted = !this.isCompleted;
    }

    @Override
    public String toString() {
        return String.format("%c %s", getStatusIcon(), getDescription());
    }

    /**
     * Converts the current task to a {@link SavedTask} object for storage.
     */
    public abstract SavedTask toSavedTask();
}
