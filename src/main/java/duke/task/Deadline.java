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
        return ("D [" + (isDone? "u\2718" : " ") + "]");
    }
    public String getBy() {
        return ("by: " + by);
    }
    @Override
    public String toString() {
        return this.getStatus() + " " + description + " " + getBy();
    }
}
