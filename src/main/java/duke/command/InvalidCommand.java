package duke.command;

import duke.util.TaskList;

/**
 * A <code>InvalidCommand</code> object takes care of the processing of invalid commands.
 */
public class InvalidCommand extends Command {
    @Override
    public void run(TaskList taskList) {
        System.out.println("I am not a chat bot, please do not chat to me.");
        }
}
