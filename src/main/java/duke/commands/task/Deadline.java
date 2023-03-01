package duke.commands.task;

import duke.commands.CommandResult;

public class Deadline extends Task {

    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_ADD_DEADLINE_SUCCESS = "Added deadline: %1$s";
    public Deadline addDeadline;

    public String by;

    public Deadline(String taskDescription, String by) {
        super(taskDescription);
        this.taskChar = "[D]";
        this.by = by;
        setFormattedTask();
    }

    public void setFormattedTask() {
        formattedTask = taskChar + status + " " + taskDescription
                + " (by: " + by + ")";
    }

    public CommandResult execute() {
        addDeadline = this;
        taskList.addTask(addDeadline);
        return new CommandResult(String.format(MESSAGE_ADD_DEADLINE_SUCCESS, formattedTask));
    }

}
