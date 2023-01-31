package wilsonoh.sagyo.commands;

import java.util.ArrayList;

import wilsonoh.sagyo.exceptions.InvalidTaskException;
import wilsonoh.sagyo.tasks.Task;

public class DeleteTaskCommand extends Command {

    private ArrayList<Task> tasks;
    private int idx;
    private Task toDelete;

    public DeleteTaskCommand(ArrayList<Task> tasks, String idxGroup) throws InvalidTaskException {
        if (idxGroup == null) {
            throw new InvalidTaskException("The delete command must be followed by the index of a task");
        }
        int idx = Integer.parseInt(idxGroup) - 1;
        if (idx < 0 || idx > tasks.size() - 1) {
            throw new InvalidTaskException(
                String.format("Task index %d is invalid for task list of size %d", idx, tasks.size()));
        }
        this.tasks = tasks;
        this.idx = idx;
        this.toDelete = tasks.get(idx);
    }

    @Override
    public void executeCommand() {
        tasks.remove(idx);
    }

    @Override
    public String[] getCommandMessage() {
        return new String[] {"Noted. I've removed this task:", this.toDelete.toString(),
                             String.format("Now you have %d tasks in the list.", tasks.size())};
    }
}
