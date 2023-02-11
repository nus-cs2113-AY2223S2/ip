package wilsonoh.sagyo.commands;

import wilsonoh.sagyo.tasklist.TaskList;
import wilsonoh.sagyo.tasks.Task;

public class MarkTaskCommand extends Command {

    private final Task toMark;
    private final int idx;
    private final TaskList tasks;

    public MarkTaskCommand(TaskList tasks, int idx) {
        this.tasks = tasks;
        this.idx = idx;
        this.toMark = tasks.getTask(this.idx);
    }

    @Override
    public String[] getCommandMessage() {
        return new String[] {"Nice! I've marked this task as done:", toMark.toString()};
    }

    @Override
    public void executeCommand() {
        tasks.markTask(idx);
    }
}
