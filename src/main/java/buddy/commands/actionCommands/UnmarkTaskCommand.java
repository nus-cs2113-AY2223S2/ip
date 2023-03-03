package buddy.commands.actionCommands;

import buddy.commands.Command;
import buddy.tasks.*;

public class UnmarkTaskCommand extends Command {
    @Override
    public void executeCommand(TaskList taskList, String input) {
        try{
            String[] unmarkSplit = input.split(" ", 2);
            int taskNumberToBeUnmarked = Integer.parseInt(unmarkSplit[1]);
            int indexOfTaskToBeUnmarked = taskNumberToBeUnmarked - 1;
            Task taskToBeUnmarked = taskList.get(indexOfTaskToBeUnmarked);
            taskToBeUnmarked.markAsUndone();


        }catch(IndexOutOfBoundsException e){
            System.out.println("That is not a valid task to unmark! Please check your list again and input a valid task");
        }

    }
}