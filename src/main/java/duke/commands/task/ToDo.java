package duke.commands.task;

import duke.commands.CommandResult;

public class ToDo extends Task {

    public static final String COMMAND_WORD = "todo";
    public static final String MESSAGE_ADD_TODO_SUCCESS = "Added todo: %1$s";
    public ToDo addToDo;

    public ToDo(String taskDescription) {
        super(taskDescription);
        this.taskChar = "[T]";
        setFormattedTask();
    }

    public void setFormattedTask() {
        formattedTask = taskChar + status + " " + taskDescription;
    }

    public CommandResult execute() {
        addToDo = this;
        taskList.addTask(addToDo);
        return new CommandResult(String.format(MESSAGE_ADD_TODO_SUCCESS, formattedTask));
    }

}
