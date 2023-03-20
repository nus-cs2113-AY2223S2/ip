package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.tasks.Task;

import java.util.List;

public class MarkCommand extends Command {
    private int taskIdentifier;

    public MarkCommand(String command) {
        try {
            taskIdentifier = Integer.parseInt(command.split(" ")[1]);
        } catch (NumberFormatException e) {
            System.out.println(">.< The task identifier supposed to be number! Here is the correct format" +
                    " \"done <number>\"");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(">.< You can't leave the task identifier empty! The format supposed to " +
                    "be \"done <number>\"");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Marks the the specified task based on the number
     *
     * @param tasks   the current state of TaskList.
     * @param storage the current state of Storage.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        List<Task> list = tasks.getList();
        if (taskIdentifier > 0 && taskIdentifier <= list.size()) {
            list.get(taskIdentifier - 1).markAsDone();
            System.out.println("Noted marking the task now!!");
            System.out.println(list.get(taskIdentifier - 1).getTask());
        } else {
            System.out.println("Sorry, I can't find the task :(");
        }
        tasks.setList(list);
    }
}