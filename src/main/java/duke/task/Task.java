package duke.task;

import duke.exception.DukeException;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() throws DukeException {
        if (this.isDone == true) {
            throw new DukeException();
        }
        this.isDone = true;
    }

    public void unmarkAsDone() throws DukeException {
        if (this.isDone == false) {
            throw new DukeException();
        }
        this.isDone = false;
    }

    public boolean isFound(String keyword) {
        return (description.contains(keyword));
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] ";
    }
}