package commands;

import enums.DialogueTypes;
import managers.OutputDialogueManager;
import managers.SaveManager;
import managers.TaskManager;

/**
 * Represents a command to end the currently running program.
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskManager taskList, SaveManager storage, OutputDialogueManager display) {
        display.printInteraction(DialogueTypes.GOODBYE);
    }

    /**
     * Checks if an exit command has been introduced, return true if it is given.
     *
     * @param command A Command Object that is to be checked if it is an exit command.
     * @return True if the command is an ExitCommand, else return false.
     */
    public static boolean isDone(Command command) {
        return command instanceof ExitCommand;
    }
}
