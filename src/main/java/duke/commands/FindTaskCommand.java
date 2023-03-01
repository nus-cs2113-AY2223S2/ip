package duke.commands;

import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Command for finding tasks that contains similar string specified by the user.
 */
public class FindTaskCommand extends Command{
    private String filter;

    /**
     * Constructs a command that will display tasks containing the specified string.
     *
     * @param substring The specified string by the user to find tasks which contain similar string.
     */
    FindTaskCommand(String substring){
        filter = substring;
    }

    /**
     * Displays a task list filtered based on the search string specified.
     *
     * @param taskList The task list that the command is executed on.
     */
    @Override
    public void execute(TaskList taskList){
        Ui.printFindMessage();
        int findListIndex = 0;
        for (int i = 0; i < taskList.listCount(); ++i) {
            if(taskList.getTask(i).containsFilter(filter)) {
                ++findListIndex;
                System.out.println(Integer.toString(findListIndex )+ '.' + taskList.getTask(i).toString());
            }
        }
    }
}
