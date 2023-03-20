package duke.task;

import duke.DukeException;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) throws DukeException {
        if (description.isBlank()) {
            throw new DukeException("Description can't be empty!");
        }
        this.description = description;
        isDone = false;
    }
    public void setAsDone() {
        this.isDone = true;
    }
    public void setAsNotDone() {
        this.isDone = false;
    }
    public boolean contains(String keyword) {
        if (description.toLowerCase().contains(keyword.toLowerCase())) {
            return true;
        }
        return false;
    }
}
