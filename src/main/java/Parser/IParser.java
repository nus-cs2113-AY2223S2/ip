package Parser;

public interface IParser {
    /**
     * Returns the last message obtained from user.
     * @return last message String by user
     */
    public String getMessage();
    public Command getCommand() throws EmptyCommandException;
}
