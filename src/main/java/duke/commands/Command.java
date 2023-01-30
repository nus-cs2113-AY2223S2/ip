package duke.commands;

import duke.tasks.TaskList;

import static duke.constants.Constants.LINEBREAK;

public class Command {

    /**
     * Handles the command based on user input.
     *
     * @param line User input represented as a String.
     * @param taskList List containing the tasks input by user.
     */
    public void handleCommand(String line, TaskList taskList){
        System.out.println("Please input a task!\n" + LINEBREAK);
    }
}
