package dev.joulev.archduke.tasks;

import dev.joulev.archduke.exceptions.ArchdukeException;
import dev.joulev.archduke.storage.SavedTask;

/**
 * This class represents a simple ToDo task that has no time constraints.
 */
public class ToDo extends Task {
    /**
     * Constructs a new {@link ToDo} object.
     * 
     * @param description The description of the task.
     * @throws ArchdukeException If the description is empty or {@code null}.
     */
    public ToDo(String description) throws ArchdukeException {
        super(description);
    }

    /**
     * Constructs a new {@link ToDo} object.
     * 
     * @param description The description of the task.
     * @param isCompleted Whether the task is completed.
     * @throws ArchdukeException If the description is empty or {@code null}.
     */
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
