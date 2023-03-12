package com.ethanyidong.bunny.task;

import com.ethanyidong.bunny.date.FlexibleDate;

/**
 * Represents an implementation of <code>Task</code> with a due date
 */
public class Deadline extends Task {
    private FlexibleDate by;

    /**
     * Creates a new deadline with a name and due date
     * The new deadline will be not done by default.
     *
     * @param name the name of the deadline to create
     * @param by   the due date of the deadline
     */
    public Deadline(String name, String by) {
        super(name);
        this.by = new FlexibleDate(by);
    }

    protected String label() {
        return "D";
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.by + ")";
    }
}
