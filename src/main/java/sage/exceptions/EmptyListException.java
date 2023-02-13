package sage.exceptions;

/**
 * Exception which returns an error message when operations is conducted on an empty list.
 * <p>
 * Prints exception message
 */
public class EmptyListException extends Exception {

    public void errorMsg() {
        System.out.println("There are currently no tasks in your list!");
    }
}
