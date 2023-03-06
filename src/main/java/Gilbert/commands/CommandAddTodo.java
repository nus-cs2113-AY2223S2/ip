package Gilbert.commands;

import Gilbert.messages.Messages;
import Gilbert.tasks.Todo;
import Gilbert.tasks.Task;
import Gilbert.tasks.TaskList;

import static Gilbert.messages.Messages.TODO;

public class CommandAddTodo extends Commands{
    /**
     * Executes the command and inserts a to-do into the tasklist. A message will be
     * printed to confirm the addition of the task.
     *
     * @param taskList      The current list of tasks that have been inputted.
     * @param description   The description of the task.
     */

    @Override
    public void doCommand(TaskList taskList, String description) {
        if (description.trim() != "") {
            Task todo = new Todo(description);
            taskList.addTask(todo);
            System.out.println(TODO);
            System.out.println(todo.getDesc());
            System.out.println(Messages.SPACE);
        } else {
            System.out.println(Messages.EMPTY);
        }
    }
}
