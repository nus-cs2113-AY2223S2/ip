package Alex.command;

import Alex.task.Task;
import Alex.task.TaskList;

public class FindCommand extends Command{
    public static final String COMMAND_WORD = "find";
    private static final String DOT = ".";
    private static final String NEW_LINE = System.lineSeparator();

    private String toFind;

    public FindCommand(String toFind) {
        this.toFind = toFind;
    }

    @Override
    public CommandResult execute(TaskList taskList) {
        int taskNo = 1;
        String list = "Here are the matching tasks in your list:" + NEW_LINE;
        for(Task t: taskList.getAllTasks()) {
            if (t.getDescription().toLowerCase().contains(toFind.toLowerCase())) {
                list += taskNo + DOT + t + NEW_LINE;
                taskNo += 1;
            }
        }


        return new CommandResult(list.substring(0,list.length() - 1));
    }
}