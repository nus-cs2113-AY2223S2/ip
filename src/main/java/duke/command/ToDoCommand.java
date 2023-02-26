package duke.command;

import duke.task.ToDo;
import duke.data.TaskList;
import duke.ui.*;

/**
 * Command to execute when adding new todo object to task list
 */
public class ToDoCommand extends Command {
    private ToDo todo;

    /**
     * creates a new todo object
     *
     * @param input data of given command and description
     */
    public void setToDo(String input) {
        String[] temp = input.split(" ", 2);
        String description = temp[1];
        this.todo = new ToDo(description);
    }

    /**
     * Adds a new todo object into the task list
     *
     * @param input data of given command and description
     * @param tasks task list containing all current tasks
     * @param ui    UI object to print task successfully added statement
     */
    @Override
    public void runCommand(String input, TaskList tasks, UI ui) {
        try {
            setToDo(input);
            tasks.addTask(todo);
            ui.printTaskAddedStatement(tasks, todo);
        } catch (ArrayIndexOutOfBoundsException exception) {
            Messages.emptyTodoMessage();
        }
    }
}
