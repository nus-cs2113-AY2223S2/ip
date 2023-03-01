package command;

import jonathan.Storage;
import task.Task;
import task.TaskList;
import jonathan.Ui;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

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
