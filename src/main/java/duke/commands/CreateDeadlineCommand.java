package duke.commands;

import duke.exception.EmptyCommandException;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Command for adding a deadline task to the task list.
 */
public class CreateDeadlineCommand extends Command{
    private String description;
    private String by;

    /**
     * Constructor that constructs a command that will add a deadline task to the task list.
     *
     * @param cases An array of string that contains the deadline command, description and due time.
     * @throws EmptyCommandException
     */
    public CreateDeadlineCommand(String[] cases) throws EmptyCommandException {
        if (cases.length == 1) {
            throw new EmptyCommandException();
        }
        String input = cases[1];
        String[] splitInput = input.split("/", 2);
        description = splitInput[0].trim();
        by = splitInput[1].substring(3);
    }

    /**
     * Executes the command to create a new deadline task which will be
     * added to the task list and database.
     *
     * @param taskList The task list that the new deadline task is added to.
     */
    @Override
    public void execute(TaskList taskList){
        Task currTask = new Deadline(description, by);
        taskList.addTasks(currTask);
        Ui.printAddedTaskCommand(taskList);
    }
}
