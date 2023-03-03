package duke.task;

import duke.exception.DukeException;

/**
 * The base class for all task types.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object.
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns icon that indicates the status of a task.
     * @return "X" if a task is marked as done, " " if a task is marked as not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks a task as done.
     * @throws DukeException If the task has already been marked as done.
     */
    public void markAsDone() throws DukeException {
        if (this.isDone == true) {
            throw new DukeException();
        }
        this.isDone = true;
    }

    /**
     * Marks a task as not done.
     * @throws DukeException If the task has already been marked as not done.
     */
    public void markAsNotDone() throws DukeException {
        if (this.isDone == false) {
            throw new DukeException();
        }
        this.isDone = false;
    }

    /**
     * Checks if a task description contains a keyword.
     * @param keyword The word to be looked for in the task description
     * @return True if the task description contains the keyword, false otherwise.
     */
    public boolean isFound(String keyword) {
        return (description.contains(keyword));
    }

    /**
     * Returns a string to show if a task is done or not.
     * @return String indicating the status of a task.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] ";
    }
}