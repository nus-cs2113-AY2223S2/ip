package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Errors;
import duke.ui.Ui;

public class MarkTaskCommand extends Command {
    private int index;
    private boolean isDone;

    public MarkTaskCommand(String[] command, TaskList taskList) throws DukeException {
        if (command.length < 2) {
            throw new DukeException(Errors.MISSING_INDEX.MESSAGE);
        }

        try {
            isDone = command[0].equals("mark");
            index = Integer.parseInt(command[1]) - 1;
            if (index < 0 || index >= taskList.size()) {
                throw new DukeException(Errors.INVALID_INDEX.MESSAGE);
            }
        } catch (NumberFormatException e) {
            throw new DukeException(Errors.INVALID_INDEX.MESSAGE);
        }
    }

    @Override
    public void run(TaskList taskList) {
        String taskString = taskList.setDone(index, isDone);
        Ui.printMarkTaskMessage(taskString, isDone);
    }
}
