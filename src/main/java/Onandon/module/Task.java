package Onandon.module;

import java.time.LocalDate;

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
    public String getDescription() {
        return description;
    }
    public void markAsDone() {
        isDone = true;
    }
    public void markAsUnDone() {
        isDone = false;
    }
    public LocalDate getBy() {
        return null;
    }
    public String getTo() { return ""; }
    public String getFrom() { return ""; }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]";
    }
}