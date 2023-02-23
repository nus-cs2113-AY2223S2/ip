package exception;

/**
 * An exception for the Chatbot
 */
public class DukeException extends Exception {
    String description;

    public DukeException(String description) {
        this.description = description;
    }

    /**
     * Returns the error message
     * 
     * @return The error message
     */
    public String getDescription() {
        return this.description;
    }
}
