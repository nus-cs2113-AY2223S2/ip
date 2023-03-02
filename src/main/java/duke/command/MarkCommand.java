package duke.command;

import duke.ui.Ui;
import duke.task.TaskList;
import duke.task.Tasks;

/**
 * <code>MarkCommand</code> object represents a command that executes
 * the marking of tasks in the TaskList
 */
public class MarkCommand extends Command{
    String item;
    public MarkCommand(String item){
        this.item = item;
    }
    @Override
    public void execute() {
        try {
            Tasks markTask = TaskList.getTaskList().get(Integer.parseInt(item) - 1);
            markTask.setMarked(true);
            Ui.displayMark(markTask);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number after /mark" +
                    "\nEXAMPLE: /mark 2");
        }
    }
}
