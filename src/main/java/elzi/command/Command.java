package elzi.command;

import elzi.ElziException;
import elzi.TaskList;

/**
 * @author : Steven A. O. Waskito
 * abstract class that will be inherited by
 * AddCommand, DeleteCommand, ExitCommand, FindCommand, ListCommand, SetAsDoneCommand
 **/
public abstract class Command {

    /**
     * Method to execute commands
     * @param taskList taskList ArrayList that stores tasks
     * @throws ElziException for unknown commands
     */
    public boolean execute(TaskList taskList) throws ElziException {
        throw new ElziException("Unknown Command");
    }
}
