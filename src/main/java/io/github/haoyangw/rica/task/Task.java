package io.github.haoyangw.rica.task;

public class Task {
    private static final String TYPE = "T";
    protected final String description;

    public Task(String description) {
        this.description = description;
    }

    protected String getType() {
        return Task.TYPE;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return this.getDescription();
    }
}
