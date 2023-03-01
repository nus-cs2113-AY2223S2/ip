package io.github.haoyangw.rica.input;

import io.github.haoyangw.rica.exception.RicaException;
import io.github.haoyangw.rica.task.TaskManager;
import io.github.haoyangw.rica.ui.TextUi;

/**
 * Represents a command issued by the user. <code>Command</code> implementations
 *   must have a run() method that executes the user's specified command.
 */
public abstract class Command {
    private final String command;
    private final TaskManager taskManager;
    private final TextUi textUi;
    public static final String CMD_SEPARATOR = " ";

    public Command(String command, TaskManager taskManager) {
        this.command = command;
        this.taskManager = taskManager;
        textUi = new TextUi();
    }

    /**
     * Returns the full command currently issued by the user
     *
     * @return String object representing the full command entered by the user
     */
    protected String getCommand() {
        return this.command;
    }

    /**
     * Returns an instance of TaskManager currently used by Rica in order to provide
     *   access to the user's current Tasks
     *
     * @return Instance of TaskManager currently attached to main Rica instance
     */
    protected TaskManager getTaskManager() {
        return this.taskManager;
    }

    /**
     * Returns an instance of TextUi for printing messages to the user Rica-style
     *
     * @return Instance of TextUi currently available to this Command object
     */
    protected TextUi getTextUi() {
        return this.textUi;
    }

    /**
     * Executes the command currently issued by the user, including printing any
     *   messages to inform the user of the results
     *
     * @throws RicaException If the current command fails during execution. Exact
     *   exception category will be determined in child class implementation of run()
     */
    public abstract void run() throws RicaException;
}
