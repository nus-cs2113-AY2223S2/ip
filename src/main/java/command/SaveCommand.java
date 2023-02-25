package command;


import task.TaskList;
import storage.TaskStorage;
import ui.UI;

public class SaveCommand extends Command{
    @Override
    public void execute(TaskList tasks, TaskStorage storage, UI ui) {
        try {
            storage.saveTasks(tasks.getTasks());
        } catch(java.io.IOException e) {
            ui.printSavingError(e);
        }
    }
}
