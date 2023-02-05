package dev.joulev.archduke.tasks;

import dev.joulev.archduke.exceptions.ArchdukeException;
import dev.joulev.archduke.exceptions.UserInputException;
import dev.joulev.archduke.exceptions.UserInputException.UserInputExceptionCode;
import dev.joulev.archduke.storage.SavedTask;

public abstract class Task {
    private String description;
    private boolean isCompleted;

    public Task(String description) throws ArchdukeException {
        setDescription(description);
        this.isCompleted = false;
    }

    public Task(String description, boolean isCompleted) throws ArchdukeException {
        setDescription(description);
        this.isCompleted = isCompleted;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Icon taken from
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

    public abstract SavedTask toSavedTask();
}
