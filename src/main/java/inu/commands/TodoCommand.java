package inu.commands;

import inu.storage.StorageFile;
import inu.task.TaskList;
import inu.task.Todo;

/**
 * Adds a new to-do to the task list.
 */
public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    private final String todoDescription;

    /**
     * Constructor.
     *
     * @param todoDescription description of the to-do to add.
     */
    public TodoCommand(String todoDescription) {
        this.todoDescription = todoDescription;
    }

    @Override
    public CommandResult execute(TaskList taskList) {
        Todo todoTask = new Todo(todoDescription);
        taskList.addTask(todoTask);
        StorageFile.saveTaskListToFile(taskList);
        return new CommandResult("added: " + taskList.getLastTask().toString() + "\n"
                + "Now you have " + taskList.getTaskListSize() + " tasks in your list.");

    }
}
