package command;

import task.Event;
import taskList.TaskList;

import java.util.ArrayList;

public class EventCommand extends Command {
    private final int DESCRIPTION = 1;
    private final int START_DATE = 2;
    private final int END_DATE = 3;


    public EventCommand(ArrayList<String> commands) {
        super(commands);
    }

    /**
     * Add an Event Task to Duke's TaskList.
     *
     * @param taskList The TaskList of Duke.
     * @return The message indicating the addition of an Event Task into Duke's TaskList.
     */
    @Override
    public String doCommand(TaskList taskList) {
        StringBuilder result = new StringBuilder();
        String description = getCommands().get(DESCRIPTION);
        String start = getCommands().get(START_DATE);
        String end = getCommands().get(END_DATE);
        Event eventTask = new Event(description, start, end);
        taskList.add(eventTask);
        result.append("Got it. I've added this task:\n");
        result.append(eventTask.getSummary()).append("\n");
        result.append("Now you have ").append(taskList.size()).append(" tasks in the list.\n");
        return String.valueOf(result);
    }
}
