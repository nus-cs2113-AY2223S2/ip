package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Errors;
import duke.ui.Ui;

/**
 * Command for adding a todo task to the task list.
 */
public class ToDoTaskCommand extends Command {
    private String description;

    /**
     * Constructs a command that will add a todo task to the task list.
     *
     * @param args Array that should contain the task description at index 1.
     * @throws DukeException If no description is provided for the todo task.
     */
    public ToDoTaskCommand(String[] args) throws DukeException {
        if (args.length < 2) {
            throw new DukeException(Errors.MISSING_DESCRIPTION.MESSAGE);
        }

        description = args[1];
    }

    /**
     * Adds a todo task with the description provided in the constructor to the task list.
     *
     * @param taskList The task list that the command is executed on.
     */
    @Override
    public void run(TaskList taskList) {
        String taskString = taskList.addTask(new ToDo(description));
        Ui.printAddTaskMessage(taskString, taskList);
    }
}
