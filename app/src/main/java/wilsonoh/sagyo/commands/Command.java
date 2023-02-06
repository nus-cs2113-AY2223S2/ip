package wilsonoh.sagyo.commands;

import wilsonoh.sagyo.exceptions.InvalidCommandException;

public abstract class Command {

    protected boolean isExit = false;

    public boolean isExit() {
        return this.isExit;
    }

    public abstract String[] getCommandMessage();

    public abstract void executeCommand() throws InvalidCommandException;
}
