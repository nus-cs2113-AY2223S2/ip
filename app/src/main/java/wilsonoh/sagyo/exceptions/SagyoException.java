package wilsonoh.sagyo.exceptions;

public class SagyoException extends Exception {
    SagyoException(String message) {
        super("Sagyo Error: " + message);
    }
}
