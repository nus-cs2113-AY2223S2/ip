package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.Task;
import duke.task.Todo;

/**
 * AddTodoCommand class that inherits from the AddCommand class
 * Refers to the adding of todo task type of Command
 */
public class AddTodoCommand extends AddCommand {
    /**
     * The task description the user is referring to
     */
    protected String todo;

    /**
     * Constructor to initialise the AddTodoCommand object that initialises the command with a todo description
     *
     * @param todo the description of the todo task
     */
    public AddTodoCommand(String todo) {
        this.todo = todo;
    }

    /**
     * Executes the Todo Command by creating a new Todo Task and saving it to TaskList and Database
     *
     * @param tasks    the TaskList the new Todo task is being added to
     * @param database the Storage the new Todo task is being saved to
     */
    @Override
    public void execute(TaskList tasks, Storage database) {
        Task currTask = new Todo(todo);
        addTaskToTaskList(tasks, currTask);
        addTaskToDatabase(currTask, database);
    }
}
