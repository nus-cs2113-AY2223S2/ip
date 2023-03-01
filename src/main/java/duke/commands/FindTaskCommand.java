package duke.commands;

import duke.exception.EmptyFilterException;
import duke.exception.EmptyIndexException;
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
     * @param cases An array of string that contains the command and the keyword used to filter.
     * @throws EmptyFilterException Exception thrown when user did not enter any keyword to filter.
     */
    FindTaskCommand(String[] cases) throws EmptyFilterException {
        if (cases.length == 1){
            throw new EmptyFilterException();
        }
        String substring = cases[1];
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
