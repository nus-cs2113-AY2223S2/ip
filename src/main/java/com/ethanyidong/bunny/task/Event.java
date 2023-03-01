package com.ethanyidong.bunny.task;

/**
 * Represents an implementation of <code>Task</code> with a start and end time.
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Creates a new event with a name, start, and end time.
     * The new event will be not done by default.
     * @param name the name of the event to create
     * @param from the start time of the event
     * @param to the end time of the event
     */
    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    protected String label() {
        return "E";
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
