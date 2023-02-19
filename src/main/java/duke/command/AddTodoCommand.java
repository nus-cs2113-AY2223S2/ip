package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;

public class AddTodoCommand extends Command {

    protected String todo;

    public AddTodoCommand(String todo) {
        this.todo = todo;
    }

    @Override
    public void execute(TaskList tasks, Storage database) {
        Task currTask = new Todo(todo);
        tasks.addTaskToTaskList(currTask);
        Ui.addSpecialTaskMessage(tasks);
        try {
            database.saveAddTask(currTask.getTaskString());
        } catch (IOException e) {
            Ui.updateDatabaseFailureMessage();
        }
    }
}
