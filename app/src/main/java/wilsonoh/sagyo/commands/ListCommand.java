package wilsonoh.sagyo.commands;

import java.util.ArrayList;

import wilsonoh.sagyo.tasks.Task;
import wilsonoh.sagyo.ui.TextFormatter;

public class ListCommand extends Command {

    private ArrayList<Task> tasks;

    public ListCommand(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    @Override
    public String[] getCommandMessage() {
        return tasks.isEmpty() ? new String[] {"Task list is currently empty.", "Try adding some tasks!"}
                               : TextFormatter.getFormattedTaskStrings(tasks);
    }

    @Override
    public void executeCommand() {
    }
}
