package command;

import storage.TaskStorage;
import task.Task;
import task.TaskList;
import ui.UI;

public class AddTaskCommand extends SaveCommand {
    private final Task task;
    public AddTaskCommand(Task task) {
        this.task = task;
    }

    /**
     * Add the {@link task.Task} into the {@link task.TaskList}, prints and saves it
     * @param tasks The {@link task.TaskList} that is responsible for modifying the ArrayList<Task>
     * @param storage The {@link storage.TaskStorage} that is responsible for saving and loading tasks
     * @param ui The {@link ui.UI} that is respoonsible for printing the output to the terminal
     */
    @Override
    public void execute(TaskList tasks, TaskStorage storage, UI ui) {
        tasks.addTask(task);
        ui.printAddTaskSuccess(task, tasks.getTasksCount());
        super.execute(tasks, storage,ui); //save task
    }
}
