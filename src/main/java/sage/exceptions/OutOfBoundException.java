package sage.exceptions;

/**
 * Exception which returns an error message when an out-of-bound task index is provided by the user
 */
public class OutOfBoundException extends Exception {

    public void errorMark() {
        System.out.println("Unable to mark the specified task: task index provided is invalid.");
    }

    public void errorUnmark() {
        System.out.println("Unable to unmark the specified task: task index provided is invalid.");
    }

    public void errorDelete() {
        System.out.println("Unable to delete the specified task: task index provided is invalid.");
    }

}
