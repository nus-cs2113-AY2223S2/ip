package duke.commands;
import duke.outputs.Messages;


public class ByeCommand extends Command{
    public ByeCommand(){
        super("bye");
    }

    @Override
    public CommandResult execute() {

        return new CommandResult(Messages.EXIT_MESSAGE);
    }
}
