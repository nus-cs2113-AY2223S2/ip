package duke.commands;

import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Command for displaying the tasks in the task list.
 */
public class PrintListCommand extends Command{
    /**
     * Displays the tasks in the task list.
     *
     * @param taskList The task list that the command is executed on.
     */
    @Override
    public void execute (TaskList taskList){
        Ui.printOpeningListMessage();
        for (int i = 0; i < taskList.listCount(); ++i) {
            String index = Integer.toString(i + 1);
            System.out.println(index + '.' + taskList.getTask(i).toString());
        }
    }
}
