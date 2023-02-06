package sage.exceptions;

public class EmptyListException extends Exception {
    /**
     * Exception which returns an error message when operations is conducted on an empty list.
     *
     * @return An error message when user is conducting operations on an empty list of tasks
     */
    public String errorMsg() {
        return "There are currently no tasks in your list!";
    }
}
