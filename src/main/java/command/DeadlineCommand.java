package command;

import task.Deadline;
import task.Task;

import java.util.ArrayList;

public class DeadlineCommand extends Command {

    public DeadlineCommand(ArrayList<String> commands) {
        super(commands);
    }

    @Override
    public void doCommand(ArrayList<Task> tasks) {
        String description = getCommands().get(1);
        String due = getCommands().get(2);
        Deadline deadlineTask = new Deadline(description, due);
        tasks.add(deadlineTask);
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(deadlineTask.getSummary());
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
        System.out.println("____________________________________________________________");
    }
}
