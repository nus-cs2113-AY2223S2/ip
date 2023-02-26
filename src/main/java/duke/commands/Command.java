package duke.commands;

public abstract class Command {

    /** The word used to invoke the command */
    public final String commandWord;

    protected Command(String commandWord) {
        this.commandWord = commandWord;
    }


    public abstract CommandResult execute();


    public boolean isByeCommand() {
        return this instanceof ByeCommand;
    }


    public boolean isTaskCommand() {
        return this instanceof TaskCommand;
    }
}
