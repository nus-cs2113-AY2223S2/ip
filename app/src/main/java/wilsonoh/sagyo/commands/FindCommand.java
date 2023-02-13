package wilsonoh.sagyo.commands;

import java.util.ArrayList;
import java.util.List;

import wilsonoh.sagyo.tasklist.TaskList;

public class FindCommand extends Command {

    private final String filter;
    private final TaskList tasks;

    public FindCommand(String filter, TaskList tasks) {
        this.filter = filter;
        this.tasks = tasks;
    }

    @Override
    public String[] getCommandMessage() {
        ArrayList<String> ret = new ArrayList<>(List.of(tasks.getFilteredTasksString(this.filter)));
        ret.add(0, String.format("Here are the tasks matching %s:", this.filter));
        return ret.toArray(String[] ::new);
    }

    @Override
    public void executeCommand() {
    }
}
