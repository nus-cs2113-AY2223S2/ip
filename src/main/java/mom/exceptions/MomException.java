package mom.exceptions;

public class MomException extends Exception {
    public MomException() {
        super();
    };

    public MomException(String errorMessage) {
        super("Duke error: " + errorMessage);
    }
}