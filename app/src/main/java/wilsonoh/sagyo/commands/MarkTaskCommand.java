package wilsonoh.sagyo.commands;

import wilsonoh.sagyo.exceptions.InvalidCommandException;
import wilsonoh.sagyo.tasklist.TaskList;
import wilsonoh.sagyo.tasks.Task;

public class MarkTaskCommand extends Command implements IndexedCommand {

    private final Task toMark;
    private final int idx;
    private final TaskList tasks;

    public MarkTaskCommand(TaskList tasks, String idxGroup) throws InvalidCommandException {
        this.tasks = tasks;
        this.idx = getValidatedIndex(tasks, idxGroup);
        this.toMark = tasks.getTask(this.idx);
    }

    @Override
    public String[] getCommandMessage() {
        return new String[] {"Nice! I've marked this task as done:", toMark.toString()};
    }

    @Override
    public void executeCommand() throws InvalidCommandException {
        tasks.markTask(idx);
    }
}
