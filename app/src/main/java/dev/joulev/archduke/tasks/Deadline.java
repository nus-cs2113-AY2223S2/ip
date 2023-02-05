package dev.joulev.archduke.tasks;

import dev.joulev.archduke.exceptions.ArchdukeException;
import dev.joulev.archduke.exceptions.UserInputException;
import dev.joulev.archduke.exceptions.UserInputException.UserInputExceptionCode;
import dev.joulev.archduke.storage.SavedTask;

public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) throws ArchdukeException {
        super(description);
        setBy(by);
    }

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
