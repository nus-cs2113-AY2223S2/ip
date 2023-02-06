package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Errors;
import duke.ui.Ui;

import java.util.HashMap;

public class DeadlineTaskCommand extends Command {
    private String description;
    private String by;

    public DeadlineTaskCommand(String[] command) throws DukeException {
        if (command.length < 2) {
            throw new DukeException(Errors.MISSING_DESCRIPTION.MESSAGE);
        }

        HashMap<String, String> args = Parser.parseArguments(command[1],
                new String[]{command[0], Deadline.DELIMITER_BY});
        if (args.get(command[0]).isEmpty()) {
            throw new DukeException(Errors.MISSING_DESCRIPTION.MESSAGE);
        }
        if (!args.containsKey(Deadline.DELIMITER_BY) || args.get(Deadline.DELIMITER_BY).isEmpty()) {
            throw new DukeException(Errors.MISSING_TIME.MESSAGE);
        }

        description = args.get(command[0]);
        by = args.get(Deadline.DELIMITER_BY);
    }

    @Override
    public void run(TaskList taskList) {
        String taskString = taskList.addTask(new Deadline(description, by));
        Ui.printAddTaskMessage(taskString, taskList);
    }
}
