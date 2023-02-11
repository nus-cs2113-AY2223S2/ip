package wilsonoh.sagyo.commands;

import wilsonoh.sagyo.tasklist.TaskList;
import wilsonoh.sagyo.tasks.Task;

public class UnMarkTaskCommand extends Command {

    private final TaskList tasks;
    private final int idx;
    private final Task toUnMark;

    public UnMarkTaskCommand(TaskList tasks, int idx) {
        this.tasks = tasks;
        this.idx = idx;
        this.toUnMark = tasks.getTask(idx);
    }

    @Override
    public String[] getCommandMessage() {
        return new String[] {"OK, I've marked this task as not done yet:", toUnMark.toString()};
    }

    @Override
    public void executeCommand() {
        tasks.unMarkTask(idx);
    }
}
