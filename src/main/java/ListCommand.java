import task.Task;

public class ListCommand extends Command {
    private final int taskCount;
    private final Task[] tasks;

    public ListCommand(int taskCount, Task[] tasks, String[] commands) {
        super(commands);
        this.taskCount = taskCount;
        this.tasks = tasks;
    }

    @Override
    public void doCommand() {
        System.out.println("____________________________________________________________"
                + "\nHere are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.print(i + 1);
            System.out.println(":" + tasks[i].getSummary());
        }
        System.out.println("____________________________________________________________");
    }
}
