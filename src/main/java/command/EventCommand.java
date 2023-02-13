package command;

import task.Event;
import task.Task;

import java.util.ArrayList;

public class EventCommand extends Command {

    public EventCommand(ArrayList<String> commands) {
        super(commands);
    }

    @Override
    public void doCommand(ArrayList<Task> tasks) {
        String description = getCommands().get(1);
        String start = getCommands().get(2);
        String end = getCommands().get(3);
        Event eventTask = new Event(description, start, end);
        tasks.add(eventTask);
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(eventTask.getSummary());
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
        System.out.println("____________________________________________________________");
    }
}
