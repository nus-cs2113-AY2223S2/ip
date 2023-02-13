package command;

import task.Task;
import task.Todo;

import java.util.ArrayList;

public class TodoCommand extends Command {

    public TodoCommand(ArrayList<String> commands) {
        super(commands);
    }

    @Override
    public void doCommand(ArrayList<Task> tasks) {
        String description = getCommands().get(1);
        Todo todoTask = new Todo(description);
        tasks.add(todoTask);
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(todoTask.getSummary());
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
        System.out.println("____________________________________________________________");
    }
}
