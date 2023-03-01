package duke.commands;

import duke.exception.EmptyCommandException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;
import duke.ui.Ui;

/**
 * Command for adding a todo task to the task list
 */
public class CreateTodoCommand extends Command{
    private String description;

    /**
     * Constructor to initialise the CreateToDoCommand that will add a todo task to the task list.
     *
     * @param cases Array that contains the task and description.
     * @throws EmptyCommandException The exception thrown when user enters a command with no description.
     */
    public CreateTodoCommand(String[] cases) throws EmptyCommandException {
        if (cases.length == 1) {
            throw new EmptyCommandException();
        }
        description = cases[1];
    }

    /**
     * Executes the command to create a new todo task which will be
     * added to the task list and database.
     *
     * @param taskList The task list that the new todo task is added to.
     */
    @Override
    public void execute(TaskList taskList){
        Task currTask = new Todo(description);
        taskList.addTasks(currTask);
        Ui.printAddedTaskCommand(taskList);
    }
}
