package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * @author : Steven A. O. Waskito
 * @mailto : e0851459@u.nus.edu
 * @created : 3 February 2023
 **/
public class AddCommand extends Command{
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }
    @Override
    public boolean execute(TaskList taskList) {
        taskList.addTask(task);
        Ui.printAddTask(task);
        Ui.printTaskLeft(taskList);
        return false;
    }
}
