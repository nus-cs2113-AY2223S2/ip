package command;

import exception.DukeException;
import task.Task;

import java.util.ArrayList;

public class DeleteCommand extends Command {

    public DeleteCommand(ArrayList<String> commands) {
        super(commands);
    }

    @Override
    public void doCommand(ArrayList<Task> tasks) throws DukeException {
        try {
            int taskNum = Integer.parseInt(getCommands().get(1));
            String taskSummary = tasks.get(taskNum - 1).getSummary();
            tasks.remove(taskNum - 1);
            System.out.println("____________________________________________________________");
            System.out.println("Noted. I've removed this task:");
            System.out.println(taskSummary);
            System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
            System.out.println("____________________________________________________________");
        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
            throw new DukeException("Invalid task number!");
        }
    }
}
