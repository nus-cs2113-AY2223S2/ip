package command;

import task.Event;
import task.Task;
import taskList.TaskList;

import java.util.ArrayList;

public class EventCommand extends Command {

    public EventCommand(ArrayList<String> commands) {
        super(commands);
    }

    @Override
    public String doCommand(TaskList taskList) {
        StringBuilder result = new StringBuilder();
        String description = getCommands().get(1);
        String start = getCommands().get(2);
        String end = getCommands().get(3);
        Event eventTask = new Event(description, start, end);
        taskList.add(eventTask);
        result.append("____________________________________________________________\n");
        result.append("Got it. I've added this task:\n");
        result.append(eventTask.getSummary()).append("\n");
        result.append("Now you have ").append(taskList.size()).append(" tasks in the list.\n");
        result.append("____________________________________________________________");
        return String.valueOf(result);
    }
}
