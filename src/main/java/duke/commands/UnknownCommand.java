package duke.commands;

public class UnknownCommand extends Command{

    private final String[] resultMessages;
    public UnknownCommand(String ...messages) {
        super(null);
        this.resultMessages = messages;
    }

    @Override
    public CommandResult execute() {

        return new CommandResult(resultMessages);
    }

}
