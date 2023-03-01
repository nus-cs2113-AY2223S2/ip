package command;


import task.TaskList;
import storage.TaskStorage;
import ui.UI;

public class SaveCommand extends Command{

    /**
     * Saves the ArrayList<Task> in the {@link TaskList} and prints it
     * @param tasks The {@link task.TaskList} that is responsible for modifying the ArrayList<Task>
     * @param storage The {@link storage.TaskStorage} that is responsible for saving and loading tasks
     * @param ui The {@link ui.UI} that is respoonsible for printing the output to the terminal
     */
    @Override
    public void execute(TaskList tasks, TaskStorage storage, UI ui) {
        try {
            storage.saveTasks(tasks.getTasks());
        } catch(java.io.IOException e) {
            ui.printSavingError(e);
        }
    }
}
