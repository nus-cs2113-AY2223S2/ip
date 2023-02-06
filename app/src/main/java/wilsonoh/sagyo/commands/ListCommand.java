package wilsonoh.sagyo.commands;

import wilsonoh.sagyo.tasklist.TaskList;

public class ListCommand extends Command {

    private final TaskList tasks;

    public ListCommand(TaskList taskList) {
        this.tasks = taskList;
    }

    @Override
    public String[] getCommandMessage() {
        return tasks.isEmpty() ? new String[] {"Task list is currently empty.", "Try adding some tasks!"}
                               : tasks.getTasksString();
    }

    @Override
    public void executeCommand() {
    }
}
