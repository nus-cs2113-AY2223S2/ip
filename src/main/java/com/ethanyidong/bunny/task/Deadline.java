package com.ethanyidong.bunny.task;

import com.ethanyidong.bunny.date.FlexibleDate;

public class Deadline extends Task {
    private FlexibleDate by;

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
