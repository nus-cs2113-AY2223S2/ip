package commands;

import data.TasksList;
import storage.Storage;
import tasks.Task;
import ui.Ui;

import java.util.ArrayList;

/**
 * Represents "find" command - finds a task with given term when executed
 */
public class FindCommand extends Command {
    String term;

    /**
     * @param term term to search for inside the tasks
     */
    public FindCommand(String term) {
        this.term = term;
    }

    @Override
    public void execute(TasksList tasksList, Ui ui, Storage storage) {
        String successMessage = "Here are the matching tasks in your list:\n";
        ArrayList<Task> tasks = tasksList.getTasks();
        TasksList result = new TasksList();

        for (Task task : tasks) {
            String name = task.getName();
            if (name.contains(term)) {
                result.addTask(task);
            }
        }

        if (result.getTasksCount() > 0) {
            ui.printLines(successMessage + result.toString());
        } else {
            ui.printLines("No task is found for this term");
        }
    }
}
