public class IllegalTaskOperation extends Exception {
    /**
     * Exception which returns an error message when operations is conducted on an empty list.
     *
     * @return An error message when user is conducting operations on an empty list of tasks
     */
    public String errorAlreadyUnmarked() {
        return "Task already marked as not completed!";
    }

    public String errorAlreadyMarked() {
        return "Task already marked as completed!";
    }
}
