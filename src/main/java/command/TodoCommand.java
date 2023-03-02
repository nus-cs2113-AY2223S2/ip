package command;

import task.Todo;
import taskList.TaskList;

import java.util.ArrayList;

public class TodoCommand extends Command {
    private final int DESCRIPTION = 1;

    public TodoCommand(ArrayList<String> commands) {
        super(commands);
    }

    @Override
    public String doCommand(TaskList taskList) {
        StringBuilder result = new StringBuilder();
        String description = getCommands().get(DESCRIPTION);
        Todo todoTask = new Todo(description);
        taskList.add(todoTask);
        result.append("Got it. I've added this task:\n");
        result.append(todoTask.getSummary()).append("\n");
        result.append("Now you have ").append(taskList.size()).append(" tasks in the list.\n");
        return String.valueOf(result);
    }
}
