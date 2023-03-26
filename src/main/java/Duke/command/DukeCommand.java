package Duke.command;

import Duke.DukeStorage;
import Duke.DukeUi;
import Duke.task.DukeTaskList;

/**
 * DukeCommand is the abstract class that represents a command.
 */
public abstract class DukeCommand {

    private Boolean isExit = false;
    
    /**
     * Executes the command.
     * 
     * @param tasks the task list
     * @param ui the user interface
     * @param storage the storage
     */
    public abstract void execute(DukeTaskList tasks, DukeUi ui, DukeStorage storage);
    
    /**
     * Returns whether the command is an exit command.
     * 
     * @return whether the command is an exit command
     */
    public boolean isExit() {
        return isExit;
    }
    
    /**
     * Sets the isExit attribute to true.
     */
    protected void setIsExit() {
        isExit = true;
    }

}
