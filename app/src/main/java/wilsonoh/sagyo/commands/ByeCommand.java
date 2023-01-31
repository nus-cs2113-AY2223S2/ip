package wilsonoh.sagyo.commands;

public class ByeCommand extends Command {

    @Override
    public void executeCommand() {
        System.exit(0);
    }

    @Override
    public String[] getCommandMessage() {
        return new String[] {"Bye. Hope to see you again soon!"};
    }
}
