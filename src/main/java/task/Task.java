package task;

import java.lang.NullPointerException;
import parser.InvalidCommandException;

/**
 * Task class that all other tasks will inherit from
 */
public abstract class Task{
    protected String description;
    protected boolean mark;

    public Task(String description) throws EmptyDescriptionException {
        setDescription(description);
        mark = false;
    }
    /**
     * Used for creating an empty class to be filled in Parser
     */
    public Task() {
        description = null;
        mark = false;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String newDescription) throws EmptyDescriptionException {
        if (newDescription == null || newDescription.isEmpty()) {
            throw new EmptyDescriptionException("Description given is empty!", new NullPointerException());
        }
        description = newDescription;
    }
    @Override
    public String toString() {
        return String.format("[%c] %s", getStatusIcon(), description);
    }
    public char getStatusIcon() {
        // mark done task with X
        return (mark ? 'X' : ' ');
    }
    public boolean isMark() {
        return mark;
    }
    /**
     * Marks a task as done or not done
     * @param newMark status to update task with
     * @throws TaskMarkException
     */
    public void setMark(boolean newMark) throws TaskMarkException {
        if (newMark == mark) {
            // We are trying to mark the task with the same boolean flag
            // This is a user error
            throw new TaskMarkException(String.format("The task is already marked as %s", String.valueOf(newMark)), new IllegalArgumentException());
        }
        this.mark = newMark;
    }
    /**
     * Parses user input arugment and sets the private attributes accordingly
     * @param arguments
     * @throws InvalidCommandException
     * @throws EmptyDescriptionException
     */
    public abstract void parseArgument(String arguments) throws InvalidCommandException, EmptyDescriptionException;
}
