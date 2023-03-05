package buddy.commands.actionCommands;

import buddy.exceptions.InvalidCommandException;
import buddy.messages.Messages;
import buddy.commands.Command;
import buddy.tasks.TaskList;
import buddy.tasks.Task;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

public class FindTaskCommand extends Command {

    /**
     * Process FindTaskCommand by user and finds and outputs the matching task to keyword
     * If there are no matching tasks, tells user that there are none
     *
     * @param taskList List of tasks
     * @param input    Command inputted by user
     */
    @Override
    public void executeCommand(TaskList taskList, String input) throws InvalidCommandException {
        if (input.equals("find")){
            throw new InvalidCommandException();
        }
        String keyword = input.split(" ")[1].trim().toLowerCase();
        ArrayList<Task> matchedTasks;
        matchedTasks = (ArrayList<Task>) taskList.stream()                                              // casts list to ArrayList
                .filter(t -> t.getTaskName().trim().toLowerCase().contains(keyword)).collect(toList());
        System.out.println(Messages.DIVIDER);
        if (!matchedTasks.isEmpty()) {
            System.out.println("Well, here is the list of tasks matching your keyword!");
        }
        for (Task task : matchedTasks) {
            System.out.println(task);
        }
        if (matchedTasks.isEmpty()) {
            System.out.println("Oops, there are no tasks matching the keyword! Try again with another keyword");
        }
        System.out.println(Messages.DIVIDER);
    }
}
