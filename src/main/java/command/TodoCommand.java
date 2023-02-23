package command;

import task.Task;
import task.Todo;

import java.util.ArrayList;

public class TodoCommand extends Command {

    public TodoCommand(ArrayList<String> commands) {
        super(commands);
    }

    @Override
    public String doCommand(ArrayList<Task> tasks) {
        StringBuilder result = new StringBuilder();
        String description = getCommands().get(1);
        Todo todoTask = new Todo(description);
        tasks.add(todoTask);
        result.append("____________________________________________________________\n");
        result.append("Got it. I've added this task:\n");
        result.append(todoTask.getSummary()).append("\n");
        result.append("Now you have ").append(tasks.size()).append(" tasks in the list.\n");
        result.append("____________________________________________________________");
        return String.valueOf(result);
    }
}
