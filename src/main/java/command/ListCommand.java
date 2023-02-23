package command;

import task.Task;

import java.util.ArrayList;

public class ListCommand extends Command {
    public ListCommand(ArrayList<String> commands) {
        super(commands);
    }

    @Override
    public String doCommand(ArrayList<Task> tasks) {
        StringBuilder result = new StringBuilder();
        result.append("____________________________________________________________"
                + "\nHere are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            result.append(i + 1);
            result.append(":").append(tasks.get(i).getSummary()).append("\n");
        }
        result.append("____________________________________________________________");
        return String.valueOf(result);
    }
}
