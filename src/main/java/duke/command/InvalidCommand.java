package duke.command;

import duke.error.ErrorTypes;
import duke.ui.Ui;

/**
 * Represents an invalid command and feedback to user
 */
public class InvalidCommand extends Command {

    public static final String MESSAGE = " Invalid input! Please provide a valid input!"
            + Ui.NEW_LINE + " Type \"help\" for the list of commands supported";

    public ErrorTypes error;

    public InvalidCommand(ErrorTypes error) {
        this.error = error;
    }

    /**
     * Executes the command and returns the result
     *
     * @return CommandResult with the relevant output message as its parameter
     */
    @Override
    public CommandResult execute() {
        String output;
        switch (error) {
        case INVALID_INPUT:
            output = String.join(Ui.NEW_LINE, Ui.SEGMENT_LINE, MESSAGE);
            break;
        case INVALID_DATE:
            output = String.join(Ui.NEW_LINE, Ui.SEGMENT_LINE, DateCommand.INVALID_COMMAND_MESSAGE);
            break;
        default:
            output = "";
        }
        return new CommandResult(output);
    }
}
