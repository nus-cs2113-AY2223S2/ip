package Gilbert.commands;

import Gilbert.messages.Messages;
import Gilbert.tasks.Deadline;
import Gilbert.tasks.Task;
import Gilbert.tasks.TaskList;

import static Gilbert.messages.Messages.DEADLINE;

public class CommandAddDeadline extends Commands {
    /**
     * Executes the command and inserts a deadline into the tasklist. A message will be
     * printed to confirm the addition of the task.
     *
     * @param taskList      The current list of tasks that have been inputted.
     * @param description   The description of the task.
     */

    @Override
    public void doCommand(TaskList taskList, String description) {
        if (description.trim() != "") {
            Task deadline = new Deadline(description);
            taskList.addTask(deadline);
            System.out.println(DEADLINE);
            System.out.println(deadline.getDesc());
            System.out.println(Messages.SPACE);
        } else {
            System.out.println(Messages.EMPTY);
        }
    }
}
