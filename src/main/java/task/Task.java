package task;

import java.io.Serializable;

public abstract class Task implements Serializable {
    private final String description;
    private boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void toggleMark(int value) {
        if (value == 1) {
            isDone = true;
        } else {
            isDone = false;
        }
    }

    public String getStatusIcon() {
        return (isDone ? "X" : "");
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
