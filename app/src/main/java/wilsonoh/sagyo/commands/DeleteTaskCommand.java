package wilsonoh.sagyo.commands;

import wilsonoh.sagyo.tasklist.TaskList;
import wilsonoh.sagyo.tasks.Task;

public class DeleteTaskCommand extends Command {

    private TaskList tasks;
    private int idx;
    private Task toDelete;

    public DeleteTaskCommand(TaskList tasks, int idx) {
        this.tasks = tasks;
        this.idx = idx;
        this.toDelete = tasks.getTask(idx);
    }

    @Override
    public void executeCommand() {
        tasks.deleteTask(idx);
    }

    @Override
    public String[] getCommandMessage() {
        return new String[] {"Noted. I've removed this task:", this.toDelete.toString(),
                             String.format("Now you have %d tasks in the list.", tasks.size())};
    }
}
