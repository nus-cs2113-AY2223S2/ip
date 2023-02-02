package wilsonoh.sagyo.commands;

public abstract class Command {

    protected boolean isExit = false;

    public boolean isExit() {
        return this.isExit;
    }

    public abstract String[] getCommandMessage();

    public abstract void executeCommand();
}
