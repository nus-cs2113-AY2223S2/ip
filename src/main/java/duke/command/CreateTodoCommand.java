package duke.command;

import duke.task.Task;
import duke.task.Todo;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * A <code>CreateTodoCommand</code> object takes care of the creation of new Todo tasks.
 */
public class CreateTodoCommand extends Command {
    private String description;
    private boolean isInvalid = false;

    /**
     * Creates parameters for to-do task.
     *
     * @param line Input line from user containing task details.
     * @throws IndexOutOfBoundsException if user does not provide either a description.
     */
    public CreateTodoCommand(String line) throws IndexOutOfBoundsException {
        try {
            String[] inputLines = line.split(" ", 2);
            description = inputLines[1];
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You must have a task description.");
            isInvalid = true;
        }
    }

    /**
     * Register a to-do task. Prints to the terminal to signal the
     * completion of adding the task.
     *
     * @param taskList List to store all tasks.
     */
    @Override
    public void run(TaskList taskList) {
        if (!isInvalid) {
            Task item = new Todo(description);
            taskList.lists.add(item);
            Ui.printAddTask(item);
            Ui.printListSize(taskList.lists.size());
        }
    }
}
