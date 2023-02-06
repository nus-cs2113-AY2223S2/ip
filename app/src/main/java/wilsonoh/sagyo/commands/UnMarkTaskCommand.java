package wilsonoh.sagyo.commands;

import wilsonoh.sagyo.exceptions.InvalidCommandException;
import wilsonoh.sagyo.tasklist.TaskList;
import wilsonoh.sagyo.tasks.Task;

public class UnMarkTaskCommand extends Command implements IndexedCommand {

    private final TaskList tasks;
    private final int idx;
    private final Task toUnMark;

    public UnMarkTaskCommand(TaskList tasks, String idxGroup) throws InvalidCommandException {
        this.tasks = tasks;
        this.idx = getValidatedIndex(tasks, idxGroup);
        this.toUnMark = tasks.getTask(idx);
    }

    @Override
    public String[] getCommandMessage() {
        return new String[] {"OK, I've marked this task as not done yet:", toUnMark.toString()};
    }

    @Override
    public void executeCommand() throws InvalidCommandException {
        tasks.unMarkTask(idx);
    }
}
