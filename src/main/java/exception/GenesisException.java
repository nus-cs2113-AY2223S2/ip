package exception;

public class GenesisException extends Exception {
    public GenesisException(String message) {
        super("☹ OOPS!!! " + message);
    }
}
