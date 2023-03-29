package Duke.commands;

import java.security.PublicKey;

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

    public boolean markAsDone() {
        return this.isDone = true;
    }

    public boolean unmarkAsDone() {
        return this.isDone = false;
    }

    public String getDueTime() {
        return "";
    }

    public String getStartTime() {
        return "";
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
