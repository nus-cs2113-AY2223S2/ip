package sage.exceptions;

/**
 * Exception which returns an error message when operations is conducted on an empty list.
 * <p>
 * Prints exception message
 */
public class IllegalOperationException extends Exception {

    public void errorAlreadyUnmarked() {
        System.out.println("Task already marked as not completed!");
    }

    public void errorAlreadyMarked() {
        System.out.println("Task already marked as completed!");
    }
}
