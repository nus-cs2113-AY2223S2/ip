package duke.command;

import duke.ui.Ui;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Tasks;

/**
 * <code>EventCommand</code> object represents a command that executes
 * the adding of a new Event Task to the TaskList
 */
public class EventCommand extends Command {
    String item;

    public EventCommand(String item) {
        this.item = item;
    }

    public void execute() {
        String[] eventSlashDate = item.split(" /from | /to ", 3);
        Tasks newEvent = new Event(eventSlashDate[0], false, eventSlashDate[1], eventSlashDate[2]);
        TaskList.addToList(newEvent);
        Ui.displayAddTask(newEvent);
    }
}
