package com.ip.duke.tasks;

public class Deadline extends Task {
    protected String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }
    @Override
    public String getTypeIcon() {
        return "D";
    }

    public String getDueDate() {
        return dueDate;
    }

    @Override
    public String getTask() {
        return taskTypeIcon() + isDoneIcon() + getDescription()
                + " (by: " + getDueDate() + ")";
    }
}
