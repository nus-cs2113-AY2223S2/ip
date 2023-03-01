package duke.model;

import duke.exception.InvalidCommandException;

public class Task {
    protected String taskName;
    protected boolean isDone;

    public Task() {
    }

    public Task(String[] descriptionArray) throws InvalidCommandException {
        if (descriptionArray.length < 1) {
            throw new InvalidCommandException("Description of task cannot be empty!");
        }
        this.taskName = descriptionArray[0];
        this.isDone = false;
    }

    public Task(String[] descriptionArray, boolean isDone) throws InvalidCommandException {
        this(descriptionArray);
        this.isDone = isDone;
    }


    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "]" + "\t" + this.taskName;
    }

    public String getTaskName() {
        return this.taskName;
    }
}
