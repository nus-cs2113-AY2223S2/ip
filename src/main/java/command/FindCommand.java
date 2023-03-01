package command;

import components.Storage;
import components.TaskList;
import components.Ui;
import exception.DukeException;
import exception.ErrorMessage;
import task.Task;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class FindCommand extends Command{
    public FindCommand(String[] commandFields) {
        super(commandFields);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> filteredList = (ArrayList<Task>) tasks.tasks.stream()
                .filter(t -> t.getDescription().contains((commandFields[0])))
                .collect(Collectors.toList());
        if (filteredList.size() == 0) {
            throw new DukeException(ErrorMessage.NO_MATCHING_TASKS.toString());
        } else {
            System.out.println("Here are the matching tasks in your list:\n");
            for (Task task: filteredList) {
                System.out.println((filteredList.indexOf(task) + 1) + "." + task);
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
