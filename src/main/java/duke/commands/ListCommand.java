package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.tasks.Task;

import java.util.List;

public class ListCommand extends Command {
    public ListCommand() {

    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Shows the content of the list
     *
     * @param tasks   the current state of TaskList.
     * @param storage the current state of Storage.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        List<Task> list = tasks.getList();
        int size = list.size();
        if (size == 0) {
            System.out.println("Bro you have nothing on your list... do you even do anything, add something to the list");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + "." + list.get(i).getTask());
            }
        }
    }
}