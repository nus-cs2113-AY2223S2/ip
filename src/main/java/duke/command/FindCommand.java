package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasklist.task.Task;
import duke.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> currentTasks = tasks.getTasks();
        ArrayList<Task> foundTasks = new ArrayList<>();

        for (Task currentTask : currentTasks) {
            String taskName = currentTask.getTaskName();

            if (taskName.contains(keyword)) {
                foundTasks.add(currentTask);
            }
        }

        ui.listFoundTask(foundTasks);
    }
}
