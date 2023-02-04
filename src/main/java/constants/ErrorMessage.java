package constants;

public enum ErrorMessage {
    INVALID_FORMAT("Ensure that your /from comes before your /to"),
    NO_BY("You have not provided a deadline. Ensure you have the /by keyword"),
    NO_FROM("You did not provide a /from"),
    NO_TASK("Please provide a task"),
    NO_TO("You did not provide a /to");


    public final String message;

    ErrorMessage(String message) {
        this.message = message;
    }
}
