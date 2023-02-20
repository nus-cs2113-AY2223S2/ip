package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.Task;
import duke.task.Todo;


public class AddTodoCommand extends AddCommand {

    protected String todo;

    public AddTodoCommand(String todo) {
        this.todo = todo;
    }

    @Override
    public void execute(TaskList tasks, Storage database) {
        Task currTask = new Todo(todo);
        addTaskToTaskList(tasks, currTask);
        addTaskToDatabase(currTask, database);
    }
}
