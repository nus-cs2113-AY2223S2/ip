package exception;

/**
 * An exception for the Chatbot
 */
public class DukeException extends Exception {
    protected String description;

    public DukeException(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
