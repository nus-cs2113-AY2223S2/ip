package duke.commands;

/**
 * When input format is not correct, it is an incorrect command.
 */
public class IncorrectCommand extends Command{
    public final String feedbackToUser;

    /**
     * Convenience constructor using raw values
     * @param feedbackToUser the input command guidance
     */
    public IncorrectCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    public void execute() {
        System.out.println(feedbackToUser);
    }
}
