package Exception;

/**
 * This class handles exception that is specific to Duke that it may encounter during its execution
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
