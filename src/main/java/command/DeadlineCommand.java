package command;

import task.Deadline;
import task.Task;

public class DeadlineCommand extends Command{

    public DeadlineCommand(String[] commands) {
        super(commands);
    }

    @Override
    public void doCommand(int taskCount, Task[] tasks) {
        String description = getCommands()[1];
        String due = getCommands()[2];
        Deadline deadlineTask = new Deadline(description, due);
        tasks[taskCount] = deadlineTask;
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(deadlineTask.getSummary());
        System.out.printf("Now you have %d tasks in the list.\n", taskCount+1);
        System.out.println("____________________________________________________________");
    }
}
