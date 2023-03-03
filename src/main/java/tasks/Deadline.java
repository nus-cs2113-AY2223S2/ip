package tasks;

import dukeException.DukeException;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, boolean isMark, String by) throws DukeException {
        super(description, isMark);
        this.by = by;
    }
    public String getBy() {
        return this.by;
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }
}