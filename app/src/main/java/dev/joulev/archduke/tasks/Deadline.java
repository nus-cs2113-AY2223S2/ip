package dev.joulev.archduke.tasks;

import dev.joulev.archduke.exceptions.ArchdukeException;
import dev.joulev.archduke.exceptions.UserInputException;
import dev.joulev.archduke.exceptions.UserInputException.UserInputExceptionCode;

public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) throws ArchdukeException {
        super(description);
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
}
