package elzi.command;

import elzi.ElziException;
import elzi.TaskList;

/**
 * @author : Steven A. O. Waskito
 * Class to tell the program to exit
 **/
public class ExitCommand extends Command {
    public ExitCommand() {
    }

    /**
     * Exits the program by returning true to Duke
     * @param taskList taskList ArrayList that stores tasks
     */
    @Override
    public boolean execute(TaskList taskList) {
        return true;
    }
}
