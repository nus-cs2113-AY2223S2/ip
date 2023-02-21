package inu.commands;

import inu.task.TaskList;
import inu.task.Todo;

public class TodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";

    private final String todoDescription;

    public TodoCommand(String todoDescription) {
        this.todoDescription = todoDescription;
    }

    @Override
    public CommandResult execute(TaskList taskList) {
        Todo todoTask = new Todo(todoDescription);
        taskList.addTask(todoTask);
        return new CommandResult("added: " + taskList.getLastTask().toString() + "\n"
                + "Now you have " + taskList.getTaskListSize() + " tasks in your list.");

    }
}
