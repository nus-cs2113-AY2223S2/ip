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
     * Handles the command based on user input.
     *
     * @param line User input represented as a String.
     * @param taskList List containing the tasks input by user.
     */
    public abstract void handleCommand(String line, TaskList taskList, Storage storage);
}
