package dev.joulev.archduke.storage;

import dev.joulev.archduke.exceptions.ArchdukeException;
import dev.joulev.archduke.exceptions.UnknownException;
import dev.joulev.archduke.tasks.Deadline;
import dev.joulev.archduke.tasks.Event;
import dev.joulev.archduke.tasks.Task;
import dev.joulev.archduke.tasks.ToDo;

/**
 * A different version of {@link Task} that is used for storage, as we need a
 * uniform schema for all different types of tasks to parse in and from the
 * storage file.
 */
public class SavedTask {
    public static final String TODO_IDENTIFIER = "T";
    public static final String EVENT_IDENTIFIER = "E";
    public static final String DEADLINE_IDENTIFIER = "D";

    private String type;
    private String description;
    private boolean isCompleted;
    private String from;
    private String to;
    private String by;

    /**
     * Constructs a new {@link SavedTask} object.
     * 
     * @param type        The type of the task. Possible values are
     *                    {@link #TODO_IDENTIFIER}, {@link #EVENT_IDENTIFIER}, and
     *                    {@link #DEADLINE_IDENTIFIER}.
     * @param description
     * @param isCompleted
     * @param from
     * @param to
     * @param by
     */
    public SavedTask(String type, String description, boolean isCompleted, String from, String to,
            String by) {
        this.type = type;
        this.description = description;
        this.isCompleted = isCompleted;
        this.from = from;
        this.to = to;
        this.by = by;
    }

    /**
     * Converts this {@link SavedTask} object to a {@link ToDo} object.
     * 
     * @return The {@link ToDo} object.
     * @throws ArchdukeException If the type of this {@link SavedTask} object is not
     *                           compatible with {@link ToDo}.
     */
    private ToDo convertToToDo() throws ArchdukeException {
        return new ToDo(description, isCompleted);
    }

    /**
     * Converts this {@link SavedTask} object to an {@link Event} object.
     * 
     * @return The {@link Event} object.
     * @throws ArchdukeException If the type of this {@link SavedTask} object is not
     *                           compatible with {@link Event}.
     */
    private Event convertToEvent() throws ArchdukeException {
        return new Event(description, isCompleted, from, to);
    }

    /**
     * Converts this {@link SavedTask} object to a {@link Deadline} object.
     * 
     * @return The {@link Deadline} object.
     * @throws ArchdukeException If the type of this {@link SavedTask} object is not
     *                           compatible with {@link Deadline}.
     */
    private Deadline convertToDeadline() throws ArchdukeException {
        return new Deadline(description, isCompleted, by);
    }

    /**
     * Converts this {@link SavedTask} object to a {@link Task} object.
     * 
     * @return The {@link Task} object.
     * @throws ArchdukeException If the type of this {@link SavedTask} object is not
     *                           compatible with the task type that it has, or the
     *                           task type is invalid.
     */
    public Task convertToTask() throws ArchdukeException {
        switch (type) {
        case TODO_IDENTIFIER:
            return convertToToDo();
        case EVENT_IDENTIFIER:
            return convertToEvent();
        case DEADLINE_IDENTIFIER:
            return convertToDeadline();
        default:
            throw new UnknownException("Unknown task type");
        }
    }
}
