package Gilbert.commands;

import Gilbert.messages.Messages;
import Gilbert.tasks.Event;
import Gilbert.tasks.Task;
import Gilbert.tasks.TaskList;

import static Gilbert.messages.Messages.EVENT;

public class CommandAddEvent extends Commands{
    /**
     * Executes the command and inserts an event into the tasklist. A message will be
     * printed to confirm the addition of the task.
     *
     * @param taskList      The current list of tasks that have been inputted.
     * @param description   The description of the task.
     */

    @Override
    public void doCommand(TaskList taskList, String description) {
        if (description.trim() != "") {
            Task event = new Event(description);
            taskList.addTask(event);
            System.out.println(EVENT);
            System.out.println(event.getDesc());
            System.out.println(Messages.SPACE);
        } else {
            System.out.println(Messages.EMPTY);
        }
    }
}
