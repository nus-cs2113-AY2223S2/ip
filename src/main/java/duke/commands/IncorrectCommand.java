package duke.commands;

/**
 * Handles invalid commands.
 */
public class IncorrectCommand extends Command {

    public static final String MESSAGE_INCORRECT_COMMAND = "Oops I don't understand this command!";
    public static final String MESSAGE_MISSING_ARGUMENTS = "Oops, command is missing arguments!";
    public static final String MESSAGE_INVALID_COMMAND =
            "Oops, command is invalid or might be missing arguments!";

    int error_num;
    public IncorrectCommand(int error_num) {
        this.error_num = error_num;
    }

    /**
     * Gives the corresponding message specified by the error number provided
     * @return CommandResult object containing message on feedback of invalid command given
     */

    @Override
    public CommandResult execute() {
        switch(error_num) {
        case 1:
            return new CommandResult(MESSAGE_MISSING_ARGUMENTS);
        case 2:
            return new CommandResult(MESSAGE_INVALID_COMMAND);
        default:
             return new CommandResult(MESSAGE_INCORRECT_COMMAND);

        }
    }
}
