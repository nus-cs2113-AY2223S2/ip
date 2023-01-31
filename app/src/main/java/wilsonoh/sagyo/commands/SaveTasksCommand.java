package wilsonoh.sagyo.commands;

import java.util.ArrayList;

import wilsonoh.sagyo.storage.Storage;
import wilsonoh.sagyo.tasks.Task;

public class SaveTasksCommand extends Command {

    private ArrayList<Task> tasks;
    private Storage storage;

    public SaveTasksCommand(ArrayList<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    @Override
    public void executeCommand() {
        storage.writeTasksToJSON(tasks);
    }

    @Override
    public String[] getCommandMessage() {
        return new String[] {"writing..."};
    }
}
