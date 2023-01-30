package duke.commands;

import duke.tasks.TaskList;

import static duke.constants.Constants.LINEBREAK;

public abstract class Command {

    /**
     * Handles the command based on user input.
     *
     * @param line User input represented as a String.
     * @param taskList List containing the tasks input by user.
     */
    public abstract void handleCommand(String line, TaskList taskList);
}
