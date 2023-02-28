package duke.commands;

import duke.save.Storage;
import duke.tasks.TaskList;

public abstract class Command {

    /**
     * Returns the number of words there are in a user input.
     *
     * @param line User input into the system
     * @return Number of words in the user input.
     */
    protected int getArgumentNumber(String line) {
        if (line == null || line.isEmpty()){
            return 0;
        }

        String[] words = line.split("\\s+");
        return words.length;
    }

    /**
     * Handles the command given by the user.
     *
     * @param line User input into the system.
     * @param taskList List of tasks.
     * @param storage Storage object to save the tasks to the hard disk.
     */
    public abstract void handleCommand(String line, TaskList taskList, Storage storage);
}
