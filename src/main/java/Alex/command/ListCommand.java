package Alex.command;

import Alex.task.Task;
import Alex.task.TaskList;

public class ListCommand extends Command{
    public static final String COMMAND_WORD = "list";
    private static final String DOT = ".";
    private static final String NEW_LINE = System.lineSeparator();


    /**
     * Executes the command and returns the result.
     *
     * @param taskList the taskList that contains all tasks of the user
     * @return CommandResult that will print to user all the current tasks
     */
    @Override
    public CommandResult execute(TaskList taskList) {
        int taskNo = 1;
        String list = "";
        for(Task t : taskList.getAllTasks()) {
            list += taskNo + DOT + t + NEW_LINE;
            taskNo += 1;
        }
        return new CommandResult(list.substring(0,list.length() - 1));
    }
}
