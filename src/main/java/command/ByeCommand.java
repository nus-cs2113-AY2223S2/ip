package command;

public class ByeCommand extends Command {
    public ByeCommand() {
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
