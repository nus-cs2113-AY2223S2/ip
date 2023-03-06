package Gilbert.commands;

import Gilbert.tasks.TaskList;

public abstract class Commands {
    public boolean runStatus = true;

    /**
     * Executes the command and prints the result. Subclasses will override this method.
     *
     * @param taskList      The current list of Gilbert.tasks that have been inputted.
     * @param description   The description of the task.
     */

    public abstract void doCommand(TaskList taskList, String description);

    public boolean isExit () {
        return runStatus;
    }
}
