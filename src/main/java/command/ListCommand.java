package command;

import storage.TaskStorage;
import task.TaskList;
import ui.UI;

public class ListCommand extends Command{
    /**
     * Prints the ArrayList<Task>in the {@link task.TaskList}
     * @param tasks The {@link task.TaskList} that is responsible for modifying the ArrayList<Task>
     * @param storage The {@link storage.TaskStorage} that is responsible for saving and loading tasks
     * @param ui The {@link ui.UI} that is respoonsible for printing the output to the terminal
     */
    @Override
    public void execute(TaskList tasks, TaskStorage storage, UI ui) {
        ui.printList(tasks.getTasks());
    }
}
