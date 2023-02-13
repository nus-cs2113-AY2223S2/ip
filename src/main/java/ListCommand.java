import task.Task;

public class ListCommand extends Command {
    public ListCommand(String[] commands) {
        super(commands);
    }

    @Override
    public void doCommand(int taskCount, Task[] tasks) {
        System.out.println("____________________________________________________________"
                + "\nHere are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.print(i + 1);
            System.out.println(":" + tasks[i].getSummary());
        }
        System.out.println("____________________________________________________________");
    }
}
