package duke.command;

public class HelpCommand extends Command{
    public static final String COMMAND_WORD = "help";
    public static final String COMMAND_USAGE = COMMAND_WORD
            + ": Show program usage instructions\n";

    private String buildHelpContent() {
        StringBuilder sb = new StringBuilder();
        sb.append(HelpCommand.COMMAND_USAGE).append("\n");
        sb.append(ExitCommand.COMMAND_USAGE).append("\n");
        sb.append(TodoCommand.COMMAND_USAGE).append("\n");
        return sb.toString();
    }
    @Override
    public CommandResult execute() {
        return new CommandResult(buildHelpContent());
    }
}
