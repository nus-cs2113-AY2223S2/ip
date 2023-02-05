package dev.joulev.archduke.tasks;

import dev.joulev.archduke.exceptions.ArchdukeException;
import dev.joulev.archduke.exceptions.UserInputException;
import dev.joulev.archduke.exceptions.UserInputException.UserInputExceptionCode;
import dev.joulev.archduke.storage.SavedTask;

public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) throws ArchdukeException {
        super(description);
        setFrom(from);
        setTo(to);
    }

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
        this.from = from;
    }

    public void setTo(String to) throws ArchdukeException {
        if (to == null || to.isBlank()) {
            throw new UserInputException(UserInputExceptionCode.TODO_TO_IS_EMPTY);
        }
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return String.format("E %s (from: %s to: %s)", super.toString(), getFrom(), getTo());
    }

    public SavedTask toSavedTask() {
        return new SavedTask(SavedTask.EVENT_IDENTIFIER, getDescription(), isCompleted(), getFrom(),
                getTo(), null);
    }
}
