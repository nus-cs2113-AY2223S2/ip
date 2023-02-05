package dev.joulev.archduke.storage;

import dev.joulev.archduke.exceptions.ArchdukeException;
import dev.joulev.archduke.exceptions.UnknownException;
import dev.joulev.archduke.tasks.Deadline;
import dev.joulev.archduke.tasks.Event;
import dev.joulev.archduke.tasks.Task;
import dev.joulev.archduke.tasks.ToDo;

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

    public SavedTask(String type, String description, boolean isCompleted, String from, String to,
            String by) {
        this.type = type;
        this.description = description;
        this.isCompleted = isCompleted;
        this.from = from;
        this.to = to;
        this.by = by;
    }

    private ToDo convertToToDo() throws ArchdukeException {
        return new ToDo(description, isCompleted);
    }

    private Event convertToEvent() throws ArchdukeException {
        return new Event(description, isCompleted, from, to);
    }

    private Deadline convertToDeadline() throws ArchdukeException {
        return new Deadline(description, isCompleted, by);
    }

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
