package dev.joulev.archduke.tasks;

import dev.joulev.archduke.exceptions.ArchdukeException;
import dev.joulev.archduke.storage.SavedTask;

public class ToDo extends Task {
    public ToDo(String description) throws ArchdukeException {
        super(description);
    }

    public ToDo(String description, boolean isCompleted) throws ArchdukeException {
        super(description, isCompleted);
    }

    @Override
    public String toString() {
        return String.format("T %s", super.toString());
    }

    public SavedTask toSavedTask() {
        return new SavedTask(SavedTask.TODO_IDENTIFIER, getDescription(), isCompleted(), null, null,
                null);
    }
}
