package parser;

import task.Task;

public interface IParser {
    /**
     * Returns the last message obtained from user.
     * @return last message String by user
     */
    public String getMessage();
        /**
     * Parses the user input from console and obtains the arguments.
     * @param command type of command to obtain arguments for
     * @return Argument class containing arguments from user
     */
    public Command getCommand() throws EmptyCommandException;
    public Task getTask() throws InvalidCommandException;
    public int getTaskIndex() throws InvalidCommandException;
}
