package wilsonoh.sagyo.commands;

public abstract class Command {

    public boolean isExit = false;

    public abstract String[] getCommandMessage();

    public abstract void executeCommand();
}
