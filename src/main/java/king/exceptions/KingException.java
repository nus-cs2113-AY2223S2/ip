package king.exceptions;

/**
 * Parent class for all exceptions in Duke
 *
 */
public class KingException extends Exception {
    KingException(String message) {
        super("\tKing Erratum: " + message);
    }
}
