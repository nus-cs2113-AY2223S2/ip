package exceptions;

public class UnknownException extends ArchdukeException {
    public UnknownException(String message) {
        super(message);
    }

    public String getErrorString() {
        return "An unknown error has occurred. It is likely a bug. Please report it to @joulev on GitHub with the following message: \""
                + getMessage() + "\".";
    }
}
