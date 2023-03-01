package duke.command;

import duke.task.Task;

public class Deadline extends Task {
    protected String by;

    /**
     * Initialises the deadline from the user command.
     *
     * @param taskDescription String of description of the task to be added.
     * @param by Date or Time of the deadline.
     */
    public Deadline(String taskDescription, String by) {
        super(taskDescription);
        this.by = by;
        setCommand("deadline " + taskDescription + " /by " + by);
    }

    /**
     * Makes a format of string to be printed.
     *
     * @return String of deadline with format.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

}
