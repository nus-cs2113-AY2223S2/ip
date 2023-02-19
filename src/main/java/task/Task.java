package task;

import duke.PrintOperations;
import error.DukeAlreadyMarkedException;

public class Task {
    private String description;
    private boolean isDone;
    private char taskType;

    public Task(String description, char taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isDone() {
        return isDone;
    }

    public char getTaskType() {
        return taskType;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() throws DukeAlreadyMarkedException {
        if (this.isDone) {
            throw new DukeAlreadyMarkedException();
        }
        this.isDone = true;
        PrintOperations.taskMarked(this);
    }

    public void markAsNotDone() throws DukeAlreadyMarkedException {
        if (!this.isDone) {
            throw new DukeAlreadyMarkedException();
        }
        this.isDone = false;
        PrintOperations.taskUnmarked(this);
    }

    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }
}