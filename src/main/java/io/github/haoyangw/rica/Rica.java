package io.github.haoyangw.rica;

import io.github.haoyangw.rica.exception.RicaException;
import io.github.haoyangw.rica.exception.RicaTaskException;
import io.github.haoyangw.rica.input.Command;
import io.github.haoyangw.rica.input.CommandManager;
import io.github.haoyangw.rica.task.TaskManager;
import io.github.haoyangw.rica.ui.TextUi;

import java.util.Scanner;

public class Rica {
    private static final String BYE_COMMAND = "bye";
    private final CommandManager commandManager;
    private final TaskManager taskManager;
    private final TextUi textUi;

    public Rica() {
        this.commandManager = new CommandManager();
        this.taskManager = new TaskManager();
        this.textUi = new TextUi();
    }

    private CommandManager getCommandManager() {
        return this.commandManager;
    }

    private TaskManager getTaskManager() {
        return this.taskManager;
    }

    private TextUi getTextUi() {
        return this.textUi;
    }

    /**
     * Parse the command entered into CLI and execute the corresponding actions
     */
    private void runCommands() throws RicaTaskException {
        String command = "";
        do {
            command = this.getCommandManager().getNextCommand();
            this.getTextUi().printHeader();
            try {
                Command nextCmd = CommandManager.parse(command, this.getTaskManager());
                nextCmd.run();
            } catch (RicaException exception) {
                this.getTextUi().printErrorMessage(exception);
            }
            this.getTextUi().printFooter();
        } while (!command.equals(Rica.BYE_COMMAND));
    }

    public void start() {
        this.getTextUi().printWelcomeMessage();
        try {
            this.runCommands();
        } catch (RicaException exception) {
            this.getTextUi().printErrorMessage(exception);
        }
    }

    public static void main(String[] args) {
        new Rica().start();
    }

}
