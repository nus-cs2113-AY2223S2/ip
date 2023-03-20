package duke.task;

import duke.DukeException;

public class Todo extends Task{
    public Todo(String description) throws DukeException {
        super(description);
    }

    public String getStatus() {
        return ("[T][" + (isDone? "X" : " ") + "]");
    }
    @Override
    public String toString() {
        return this.getStatus() + " " + description;
    }
    @Override
    public String encode() {
        return ("T_" + (isDone ?"1_":"0_")
                + description);
    }
    @Override
    public String getType() {
        return "T";
    }
}
