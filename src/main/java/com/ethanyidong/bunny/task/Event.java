package com.ethanyidong.bunny.task;

import com.ethanyidong.bunny.date.FlexibleDate;

public class Event extends Task {
    private FlexibleDate from;
    private FlexibleDate to;

    public Event(String name, String from, String to) {
        super(name);
        this.from = new FlexibleDate(from);
        this.to = new FlexibleDate(to);
    }

    protected String label() {
        return "E";
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
