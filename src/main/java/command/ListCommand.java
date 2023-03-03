package command;

import taskList.TaskList;

import java.util.ArrayList;

public class ListCommand extends Command {
    public ListCommand(ArrayList<String> commands) {
        super(commands);
    }

    /**
     * List all Duke's tasks and their properties.
     *
     * @param taskList The TaskList of Duke.
     * @return The list of Duke's tasks and their properties.
     */
    @Override
    public String doCommand(TaskList taskList) {
        StringBuilder result = new StringBuilder();
        result.append("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            result.append(i + 1);
            result.append(":").append(taskList.get(i).getSummary()).append("\n");
        }
        return String.valueOf(result);
    }
}
