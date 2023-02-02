package wilsonoh.sagyo.commands;

import java.util.ArrayList;

import wilsonoh.sagyo.exceptions.InvalidTaskException;
import wilsonoh.sagyo.tasks.Task;

public class MarkTaskCommand extends Command {

    private final ArrayList<Task> tasks;
    private final int idx;

    public MarkTaskCommand(ArrayList<Task> tasks, String idxGroup) throws InvalidTaskException {
        if (idxGroup == null) {
            throw new InvalidTaskException("The mark command must be followed by the index of a task");
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
        return new String[] {"Nice! I've marked this task as done:", "  " + tasks.get(idx)};
    }

    @Override
    public void executeCommand() {
        tasks.get(idx).markDone();
    }
}
