package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Errors;
import duke.ui.Ui;

public class ToDoTaskCommand extends Command {
    private String description;

    public ToDoTaskCommand(String[] command) throws DukeException {
        if (command.length < 2) {
            throw new DukeException(Errors.MISSING_DESCRIPTION.MESSAGE);
        }

        description = command[1];
    }

    @Override
    public void run(TaskList taskList) {
        String taskString = taskList.addTask(new ToDo(description));
        Ui.printAddTaskMessage(taskString, taskList);
    }
}
