package Command;

import Entities.Task;
import Entities.TaskList;
import Exceptions.DukeException;
import FileUtils.Storage;
import Output.UI;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        Task removedTask = tasks.deleteTask(taskIndex);
        ui.taskDeletedMessage(removedTask, tasks.getTasksCount());
        storage.write(tasks);
    }
}
