package duke.command;

import duke.ui.Ui;

/**
 * sShows help instructions
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = " help: view all commands available. "
            + Ui.NEW_LINE + "  Example: help";

    /**
     * Executes the command and returns the result
     *
     * @return CommandResult with the relevant output message as its parameter
     */
    @Override
    public CommandResult execute() {
        String output = Ui.SEGMENT_LINE;
        output = String.join(Ui.NEW_LINE, output, (AddCommand.TODO_MESSAGE_USAGE
                + Ui.NEW_LINE + Ui.NEW_LINE + AddCommand.DEADLINE_MESSAGE_USAGE
                + Ui.NEW_LINE + Ui.NEW_LINE + AddCommand.EVENT_MESSAGE_USAGE
                + Ui.NEW_LINE + Ui.NEW_LINE + ListCommand.MESSAGE_USAGE
                + Ui.NEW_LINE + Ui.NEW_LINE + MarkCommand.MESSAGE_USAGE
                + Ui.NEW_LINE + Ui.NEW_LINE + UnmarkCommand.MESSAGE_USAGE
                + Ui.NEW_LINE + Ui.NEW_LINE + DeleteCommand.MESSAGE_USAGE
                + Ui.NEW_LINE + Ui.NEW_LINE + FindCommand.MESSAGE_USAGE
                + Ui.NEW_LINE + Ui.NEW_LINE + DateCommand.MESSAGE_USAGE
                + Ui.NEW_LINE + Ui.NEW_LINE + HelpCommand.MESSAGE_USAGE
                + Ui.NEW_LINE + Ui.NEW_LINE + ExitCommand.MESSAGE_USAGE));
        return new CommandResult(output);
    }
}
