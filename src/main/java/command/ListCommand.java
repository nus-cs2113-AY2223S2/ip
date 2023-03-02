package command;

import exception.DukeException;
import exception.ErrorMessage;
import task.Task;
import components.TaskList;
import components.UI;
import components.Storage;

public class ListCommand extends Command {
    public ListCommand() {
    }

    /**
     * Prints the items in the ArrayList.
     *
     * @param tasks ArrayList of tasks.
     * @param ui Deals with interactions with the user.
     * @param storage Deals with saving and loading tasks in the file.
     * @throws DukeException If the list is empty.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        if (tasks.tasks.size() > 0) {
            for (Task task : tasks.tasks) {
                System.out.println((tasks.tasks.indexOf(task) + 1) + "." + task);
            }
        } else {
            throw new DukeException(ErrorMessage.EMPTY_LIST.toString());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
