package Duke.command;

import static Duke.string.Strings.LINE_SEPARATOR;

import Duke.DukeException;
import Duke.DukeStorage;
import Duke.DukeUi;
import Duke.task.DukeTaskList;

/**
 * DukeMarkCommand is the class that represents a mark command.
 */
public class DukeMarkCommand extends DukeCommand {

    private int index;

    /**
     * Constructor for DukeMarkCommand.
     * 
     * @param index the index of the task to be marked
     * @throws DukeException if the index is not in the correct format
     */
    public DukeMarkCommand(String index) throws DukeException {
        try {
            this.index = Integer.parseInt(index);
        } catch (NumberFormatException e) {
            throw new DukeException("The index of the task to be marked must be an integer.");
        }
    }

    @Override
    public void execute(DukeTaskList tasks, DukeUi ui, DukeStorage storage) {
        if (index > tasks.tasksList.size() || index <= 0) {
            ui.showError("The index of the task to be marked is out of bounds.");
        } else {
            tasks.tasksList.get(index - 1).mark();
            System.out.println("Nice! I've marked this task as done:" + LINE_SEPARATOR
                    + "  " + tasks.tasksList.get(index - 1));
        }
    }

}
