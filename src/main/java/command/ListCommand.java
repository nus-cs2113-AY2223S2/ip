package command;

import task.Task;

import java.util.ArrayList;

public class ListCommand extends Command {
    public ListCommand(ArrayList<String> commands) {
        super(commands);
    }

    @Override
    public void doCommand(ArrayList<Task> tasks) {
        System.out.println("____________________________________________________________"
                + "\nHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print(i + 1);
            System.out.println(":" + tasks.get(i).getSummary());
        }
        System.out.println("____________________________________________________________");
    }
}
