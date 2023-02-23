package command;

import task.Deadline;
import task.Task;
import taskList.TaskList;

import java.util.ArrayList;

public class DeadlineCommand extends Command {

    public DeadlineCommand(ArrayList<String> commands) {
        super(commands);
    }

    @Override
    public String doCommand(TaskList taskList) {
        StringBuilder result = new StringBuilder();
        String description = getCommands().get(1);
        String due = getCommands().get(2);
        Deadline deadlineTask = new Deadline(description, due);
        taskList.add(deadlineTask);
        result.append("____________________________________________________________\n");
        result.append("Got it. I've added this task:\n");
        result.append(deadlineTask.getSummary()).append("\n");
        result.append("Now you have ").append(taskList.size()).append(" tasks in the list.\n");
        result.append("____________________________________________________________");
        return String.valueOf(result);
    }
}
