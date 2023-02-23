package duke.command;

import duke.task.Task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String taskDescription, String by) {
        super(taskDescription);
        this.by = by;
        setCommand("deadline " + taskDescription + " /by " + by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

}
