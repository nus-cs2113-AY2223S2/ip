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
    /**
     * Determines the current type of task and calls the task constructor
     * to generate a task object.
     * @return
     * @throws InvalidCommandException
     */
    public Task getTask() throws InvalidCommandException;
    /**
     * Returns the index of the task specified by user input
     * @return int value specifying index of task
     * @throws InvalidCommandException
     */
    public int getTaskIndex() throws InvalidCommandException;
}
