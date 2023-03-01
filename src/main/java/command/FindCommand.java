package command;

import exception.InvalidIndexException;
import storage.TaskStorage;
import task.Task;
import task.TaskList;
import ui.UI;

import java.util.ArrayList;

public class FindCommand extends Command{
    private String findDesc;

    public FindCommand(String findDesc) {
        this.findDesc = findDesc;
    }

    /**
     * Finds the {@link task.Task} in the {@link task.TaskList} and prints it
     * @param tasks The {@link task.TaskList} that is responsible for modifying the ArrayList<Task>
     * @param storage The {@link storage.TaskStorage} that is responsible for saving and loading tasks
     * @param ui The {@link ui.UI} that is respoonsible for printing the output to the terminal
     */
    @Override
    public void execute(TaskList tasks, TaskStorage storage, UI ui) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task t: tasks.getTasks()) {
            if (t.getDescription().contains(findDesc)) {
                foundTasks.add(t);
            }
        }
        ui.printFoundTasks(foundTasks);
    }
}


