package duke.command;

public class ExitCommand extends Command {
    /**
     * Initialises an instance of ExitCommand.
     */
    public ExitCommand() {

    }

    /**
     * Returns true to make Duke chatbot exit.
     *
     * @return true to make Duke chatbot exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
