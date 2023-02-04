package tasks;

import exceptions.ArchdukeException;
import exceptions.UserInputException;
import exceptions.UserInputException.UserInputExceptionCode;

public abstract class Task {
    private String description;
    private boolean isCompleted;

    public Task(String description) throws ArchdukeException {
        setDescription(description);
        this.isCompleted = false;
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
}
