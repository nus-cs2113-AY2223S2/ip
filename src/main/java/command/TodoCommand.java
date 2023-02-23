package command;

import task.Task;
import task.Todo;
import taskList.TaskList;

import java.util.ArrayList;

public class TodoCommand extends Command {

    public TodoCommand(ArrayList<String> commands) {
        super(commands);
    }

    @Override
    public String doCommand(TaskList taskList) {
        StringBuilder result = new StringBuilder();
        String description = getCommands().get(1);
        Todo todoTask = new Todo(description);
        taskList.add(todoTask);
        result.append("____________________________________________________________\n");
        result.append("Got it. I've added this task:\n");
        result.append(todoTask.getSummary()).append("\n");
        result.append("Now you have ").append(taskList.size()).append(" tasks in the list.\n");
        result.append("____________________________________________________________");
        return String.valueOf(result);
    }
}
