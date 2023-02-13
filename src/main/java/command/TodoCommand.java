package command;

import task.Task;
import task.Todo;

public class TodoCommand extends Command{

    public TodoCommand(String[] commands) {
        super(commands);
    }

    @Override
    public void doCommand(int taskCount, Task[] tasks) {
        String description = getCommands()[1];
        Todo todoTask = new Todo(description);
        tasks[taskCount] = todoTask;
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(todoTask.getSummary());
        System.out.printf("Now you have %d tasks in the list.\n", taskCount+1);
        System.out.println("____________________________________________________________");
    }
}
