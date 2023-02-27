package io.github.haoyangw.rica.input;

import io.github.haoyangw.rica.exception.RicaException;
import io.github.haoyangw.rica.task.TaskManager;
import io.github.haoyangw.rica.ui.TextUi;

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

    protected String getCommand() {
        return this.command;
    }

    protected TaskManager getTaskManager() {
        return this.taskManager;
    }

    protected TextUi getTextUi() {
        return this.textUi;
    }

    public abstract void run() throws RicaException;
}
