package command;

import components.Storage;
import components.TaskList;
import components.UI;
import exception.DukeException;
import exception.ErrorMessage;
import task.Task;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FindCommand extends Command {
    public FindCommand(String[] commandFields) {
        super(commandFields);
    }

    /**
     * Prints out a list of matching tasks based on user input.
     *
     * @param tasks   ArrayList of tasks.
     * @param ui      Deals with interactions with the user.
     * @param storage Deals with saving and loading tasks in the file.
     * @throws DukeException If there is no matching tasks.
     */
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        ArrayList<Task> filteredList = (ArrayList<Task>) tasks.tasks.stream()
                .filter(t -> t.getDescription().contains((commandFields[0])))
                .collect(Collectors.toList());
        if (filteredList.size() == 0) {
            throw new DukeException(ErrorMessage.NO_MATCHING_TASKS.toString());
        } else {
            System.out.println("Here are the matching tasks in your list:\n");
            for (Task task : filteredList) {
                ui.printTaskInList(tasks, task);
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
