package duke.commands.task;

import duke.commands.CommandResult;

/**
 * Represents a to-do task in the task list.
 */

public class ToDo extends Task {

    public static final String COMMAND_WORD = "todo";
    public static final String MESSAGE_ADD_TODO_SUCCESS = "Added todo: %1$s";
    public ToDo addToDo;

    public ToDo(String taskDescription, String taskStatus) {
        super(taskDescription, taskStatus);
        this.taskChar = "[T]";
        setFormattedTask();
    }


    /**
     * Sets and updates any changes to the string representing the to-do task when called
     */

    public void setFormattedTask() {
        formattedTask = taskChar + status + " " + taskDescription;
    }

    /**
     * Adds a to-do task into the task list
     * @return CommandResult object containing feedback message
     */
    public CommandResult execute() {
        addToDo = this;
        taskList.addTask(addToDo);
        return new CommandResult(String.format(MESSAGE_ADD_TODO_SUCCESS, formattedTask));
    }

}
