package wilsonoh.sagyo.commands;

public class ByeCommand extends Command {

    public ByeCommand() {
        this.isExit = true;
    }

    @Override
    public void executeCommand() {
    }

    @Override
    public String[] getCommandMessage() {
        return new String[] {"Bye. Hope to see you again soon!"};
    }
}
