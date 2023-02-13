import task.Event;
import task.Task;

public class EventCommand extends Command {

    public EventCommand(String[] commands) {
        super(commands);
    }

    @Override
    public void doCommand(int taskCount, Task[] tasks) {
        String description = getCommands()[1];
        String start = getCommands()[2];
        String end = getCommands()[3];
        Event eventTask = new Event(description,start,end);
        tasks[taskCount] = eventTask;
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(eventTask.getSummary());
        System.out.printf("Now you have %d tasks in the list.\n", taskCount);
        System.out.println("____________________________________________________________");
    }
}
