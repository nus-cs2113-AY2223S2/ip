package wilsonoh.sagyo.commands;

public abstract class Command {

    public abstract String[] getCommandMessage();

    public abstract void executeCommand();
}
