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
