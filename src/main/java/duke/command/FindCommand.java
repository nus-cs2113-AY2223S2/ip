package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasklist.task.Task;
import duke.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;

    /**
     * Initialises an instance of FindCommand.
     * Stores keyword into the instance of FindCommand.
     *
     * @param keyword Keyword to find tasks in the task array list.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes find task command.
     * Find tasks in the task array list based on the keyword.
     * Print the results on the CLI.
     *
     * @param tasks Instance of task list containing task array list.
     * @param ui Instance of ui.
     * @param storage Instance of storage.
     */
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
