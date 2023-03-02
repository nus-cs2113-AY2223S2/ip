package duke.command;

import duke.ui.Ui;
import duke.task.Dateline;
import duke.task.TaskList;
import duke.task.Tasks;
/**
 * <code>DeadlineCommand</code> object represents a command that executes
 * the adding of a new Deadline Task to the TaskList
 */
public class DeadlineCommand extends Command{
    String item;
    public DeadlineCommand(String item) {
        this.item = item;
    }
    public void execute() {
        String[] taskDate = item.split(" /by ", 2);
        Tasks newDeadline = new Dateline(taskDate[0], false, taskDate[1]);
        TaskList.addToList(newDeadline);
        Ui.displayAddTask(newDeadline);
    }
}
