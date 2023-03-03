package duke.Commands;

import duke.Storage;
import dukeException.DukeException;
import tasks.Todo;

/*
 * Represents adding a todo task
 * extends Command
 * */
public class ToDoCommand extends Command {
    /**
     * The string representing the command word for this command.
     */
    public static final String COMMAND_WORD = "todo";

    /**
     * The description of the new Todo task.
     */
    private String desc;

    /**
     * The completion status of the new Todo task.
     */
    private boolean isMark;

    /**
     * Constructs a new ToDoCommand object.
     *
     * @param desc   The description of the new Todo task.
     * @param isMark The completion status of the new Todo task.
     */
    public ToDoCommand(String desc, boolean isMark) {
        this.desc = desc;
        this.isMark = isMark;
    }

    /**
     * Executes the ToDoCommand to add a new Todo task to the task list and save the changes.
     *
     * @throws DukeException If there is an error adding the new Todo task to the task list.
     */
    public void cmd() throws DukeException {
        Todo todo = new Todo(this.desc, this.isMark);
        tasks.add(todo);
        addTaskPrint(tasks, todo);
        Storage.saveTasks(tasks);
    }
}
