package duke.commands.task;

import duke.commands.CommandResult;

/**
 * Represents a deadline task in the task list.
 */

public class Deadline extends Task {

    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_ADD_DEADLINE_SUCCESS = "Added deadline: %1$s";
    public Deadline addDeadline;
    public String by;

    public Deadline(String taskDescription, String taskStatus, String by) {
        super(taskDescription, taskStatus);
        this.taskChar = "[D]";
        this.by = by;
        setFormattedTask();
    }

    /**
     * Sets and updates any changes to the string representing the deadline task when called
     */

    public void setFormattedTask() {
        formattedTask = taskChar + status + " " + taskDescription
                + " (by: " + by + ")";
    }

    /**
     * Adds a deadline task into the task list
     * @return CommandResult object containing feedback message
     */

    public CommandResult execute() {
        addDeadline = this;
        taskList.addTask(addDeadline);
        return new CommandResult(String.format(MESSAGE_ADD_DEADLINE_SUCCESS, formattedTask));
    }

}
