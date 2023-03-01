package command;

import exception.DukeException;
import exception.ErrorMessage;
import task.Task;
import components.TaskList;
import components.Ui;
import components.Storage;


public class ListCommand extends Command {
    public ListCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
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
