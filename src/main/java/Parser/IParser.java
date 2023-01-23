package Parser;

public interface IParser {
    /**
     * Gets the a message from console that user inputs.
     * Checks if the input message is empty and raises Exception if empty.
     * @exception EmptyCommandException
     */
    public void getNextMessage() throws EmptyCommandException;
    /**
     * Returns the last message obtained from user.
     * @return last message String by user
     */
    public String getMessage();
    /**
     * Test if the last message obtained contains keyword "bye"
     * @return boolean
     */
    public boolean isExit();
}
