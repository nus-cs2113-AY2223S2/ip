package command;

import jonathan.Storage;
import task.Task;
import task.TaskList;
import jonathan.Ui;

/**
 * model a class to handle the find command. inherit from Command class.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * build constructor for FindCommand class.
     * @param keyword to be searched.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Method to execute the find command class.
     * @param tasks list of tasks.
     * @param ui the interface of the program.
     * @param storage the storage of the program.
     * @return list of tasks.
     */
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchedTasks = new TaskList();

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.toString().contains(keyword)) {
                matchedTasks.add(task);
            }
        }

        ui.showMatchedTask(matchedTasks);
        return tasks;
    }

}
