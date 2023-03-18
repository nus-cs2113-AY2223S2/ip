package Duke.command;

import static Duke.string.Strings.LINE_SEPARATOR;

import Duke.DukeException;
import Duke.DukeStorage;
import Duke.DukeUi;
import Duke.task.DukeTaskList;

/**
 * DukeUnmarkCommand is the class that represents an unmark command.
 */
public class DukeUnmarkCommand extends DukeCommand {

    private int index;

    /**
     * Constructor for DukeUnmarkCommand.
     * 
     * @param index the index of the task to be unmarked
     * @throws DukeException if the index is not in the correct format
     */
    public DukeUnmarkCommand(String index) throws DukeException {
        try {
            this.index = Integer.parseInt(index);
        } catch (NumberFormatException e) {
            throw new DukeException("The index of the task to be unmarked must be an integer.");
        }
    }

    @Override
    public void execute(DukeTaskList tasks, DukeUi ui, DukeStorage storage) {
        if (index > tasks.tasksList.size() || index <= 0) {
            ui.showError("The index of the task to be unmarked is out of bounds.");
        } else {
            tasks.tasksList.get(index - 1).unmark();
            System.out.println("Nice! I've unmarked this task as done:" + LINE_SEPARATOR
                    + "  " + tasks.tasksList.get(index - 1));
        }
    }

}
