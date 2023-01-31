package wilsonoh.sagyo.commands;

import java.util.ArrayList;

import wilsonoh.sagyo.exceptions.InvalidTaskException;
import wilsonoh.sagyo.tasks.Task;

public class UnMarkTaskCommand extends Command {

    private ArrayList<Task> tasks;
    private int idx;

    public UnMarkTaskCommand(ArrayList<Task> tasks, String idxGroup) throws InvalidTaskException {
        if (idxGroup == null) {
            throw new InvalidTaskException("The unmark command must be followed by the index of a task");
        }
        int idx = Integer.parseInt(idxGroup) - 1;
        if (idx < 0 || idx > tasks.size() - 1) {
            throw new InvalidTaskException(
                String.format("Task index %d is invalid for task list of size %d", idx, tasks.size()));
        }
        this.tasks = tasks;
        this.idx = idx;
    }

    @Override
    public String[] getCommandMessage() {
        return new String[] {"OK, I've marked this task as not done yet:", "  " + tasks.get(idx)};
    }

    @Override
    public void executeCommand() {
        tasks.get(idx).unMarkDone();
    }
}
