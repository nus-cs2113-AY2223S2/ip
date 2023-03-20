package duke.task;

import duke.DukeException;

public class Deadline extends Task{
    protected String by;
    public Deadline(String description, String by) throws DukeException {
        super(description);
        if (by.isBlank()) {
            throw new DukeException("Missing /by in deadline");
        }
        this.by = by;
    }

    public String getStatus() {
        return ("[D][" + (isDone? "X" : " ") + "]");
    }
    public String getBy() {
        return ("by: " + by);
    }
    @Override
    public String toString() {
        return this.getStatus() + " " + description + " (" + getBy() + ")";
    }
    @Override
    public String encode() {
        return ("D_" + (isDone ?"1_":"0_")
                + description + "_"
                + by);
    }
    @Override
    public String getType() {
        return "D";
    }
}
