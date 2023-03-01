package Command;

import Entities.Todo;
import Exceptions.DukeException;
import Exceptions.NoDescriptionException;

public class AddTodoCommand extends AddCommand {

    /**
     * Constructor of AddTodoCommand
     * Calls parseInput method to create the task
     * @param command command string of todo. Default: "todo"
     * @param input input of user
     * @throws DukeException
     */
    public AddTodoCommand(String command, String input) throws DukeException {
        super(command, input);
    }
    
    /**
     * Helper function to parse input string
     * Creates Todo task based on command string
     * @param command command string of todo. Default: "todo"
     * @param input input of user
     * @throws DukeException
     */
    @Override
    public void parseInput(String command, String input) throws DukeException {
        String taskDescription;

        if (input.length() == command.length()) {
            throw new NoDescriptionException(command);
        }
        taskDescription = input.substring(command.length() + 1);
        this.addedTask = new Todo(taskDescription, false);

        
    }
}
