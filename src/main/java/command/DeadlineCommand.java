package command;

import task.Deadline;
import taskList.TaskList;

import java.util.ArrayList;

public class DeadlineCommand extends Command {
    private final int DESCRIPTION = 1;
    private final int DUE = 2;

    public DeadlineCommand(ArrayList<String> commands) {
        super(commands);
    }

    /**
     * Add a Deadline Task to Duke's TaskList.
     *
     * @param taskList The TaskList of Duke.
     * @return The message indicating the addition of a Deadline Task into Duke's TaskList.
     */
    @Override
    public String doCommand(TaskList taskList) {
        StringBuilder result = new StringBuilder();
        String description = getCommands().get(DESCRIPTION);
        String due = getCommands().get(DUE);
        Deadline deadlineTask = new Deadline(description, due);
        taskList.add(deadlineTask);
        result.append("Got it. I've added this task:\n");
        result.append(deadlineTask.getSummary()).append("\n");
        result.append("Now you have ").append(taskList.size()).append(" tasks in the list.\n");
        return String.valueOf(result);
    }
}
