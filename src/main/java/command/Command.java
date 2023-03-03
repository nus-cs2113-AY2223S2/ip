package command;

import exception.DukeException;
import taskList.TaskList;

import java.util.ArrayList;

/**
 * Represent a command.
 */
public abstract class Command {
    private final ArrayList<String> COMMANDS;

    public Command(ArrayList<String> commands) {
        this.COMMANDS = commands;
    }

    /**
     * Retrieve the command and its arguments.
     *
     * @return The command and its arguments as an ArrayList of String.
     */
    public ArrayList<String> getCommands() {
        return COMMANDS;
    }

    /**
     * Execute the command using its arguments.
     *
     * @param taskList The TaskList of Duke.
     * @return The result from executing the command as a string.
     */
    public abstract String doCommand(TaskList taskList) throws DukeException;
}

