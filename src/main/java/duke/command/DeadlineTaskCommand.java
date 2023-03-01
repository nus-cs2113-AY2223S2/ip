package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Errors;
import duke.ui.Ui;

import java.util.HashMap;

/**
 * Command for adding a deadline task to the task list.
 */
public class DeadlineTaskCommand extends Command {
    private String description;
    private String by;

    /**
     * Constructs a command that will add a deadline task to the task list.
     *
     * @param args Array that should contain the task description and deadline at index 1.
     * @throws DukeException If no description or deadline is provided for the deadline task.
     */
    public DeadlineTaskCommand(String[] args) throws DukeException {
        if (args.length < 2) {
            throw new DukeException(Errors.MISSING_DESCRIPTION.MESSAGE);
        }

        HashMap<String, String> splitArgs = Parser.parseArguments(args[1],
                new String[]{args[0], Deadline.DELIMITER_BY});
        if (splitArgs.get(args[0]).isEmpty()) {
            throw new DukeException(Errors.MISSING_DESCRIPTION.MESSAGE);
        }
        if (!splitArgs.containsKey(Deadline.DELIMITER_BY) || splitArgs.get(Deadline.DELIMITER_BY).isEmpty()) {
            throw new DukeException(Errors.MISSING_TIME.MESSAGE);
        }

        description = splitArgs.get(args[0]);
        by = splitArgs.get(Deadline.DELIMITER_BY);
    }

    /**
     * Adds a deadline task with the description and deadline provided in the constructor to the task list.
     *
     * @param taskList The task list that the command is executed on.
     */
    @Override
    public void run(TaskList taskList) {
        String taskString = taskList.addTask(new Deadline(description, by));
        Ui.printAddTaskMessage(taskString, taskList);
    }
}
