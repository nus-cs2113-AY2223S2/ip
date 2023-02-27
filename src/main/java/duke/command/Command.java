package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * The Command class which is the superclass of all the other Command classes
 */
public abstract class Command {
    /**
     * To check if the Command requires the program to exit
     */
    public boolean isExit;

    /**
     * The Command constructor that first initialises the isExit boolean to false as we do not want to exit immediately
     */
    public Command() {
        isExit = false;
    }

    /**
     * Executes the Command
     *
     * @param tasks    the TaskList that is being referred to
     * @param database the Storage that is being referred to
     */
    public abstract void execute(TaskList tasks, Storage database);

}
