package duke.command;

public abstract class Command {
    public abstract boolean isExit();
    public abstract void execute (Storage storage);
}
