package duke.commands;

import duke.tasks.Todo;
import duke.outputs.Messages;
import duke.file.TaskList;
import duke.ui.UI;

/**
 * Command when adding a new todo task to tasklist
 */
public class TodoCommand extends Command {
    private Todo todo;

    /**
     * creates a new todo task
     *
     * @param input details of the user command
     */
    public void setToDo(String input) {
        String[] deconstructedDetails = input.split(" ", 2);
        String description = deconstructedDetails[1];
        this.todo = new Todo(description);
    }

    /**
     * Adds a new todo task into the tasklist
     *
     * @param input details of the user command
     * @param tasks tasklist which contains all the tasks
     * @param ui    UI to show task addition message
     */
    @Override
    public void execute(String input, TaskList tasks, UI ui) {
        try {
            setToDo(input);
            tasks.addNewTask(todo);
            ui.printTaskAddedMessage(tasks, todo);
        } catch (ArrayIndexOutOfBoundsException exception) {
            Messages.emptyTodoErrorMessage();
        }
    }
}
