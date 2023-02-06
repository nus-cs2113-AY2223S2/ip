public class InvalidTaskException extends Exception {
    /**
     * Exception which returns an error message when an out-of-bound task index is provided by the user
     *
     * @return An error message when user provides a task index that is out of bound
     */
    public String errorMark() {
        return "Unable to mark the specified task: task index provided is invalid.";
    }

    public String errorUnmark() {
        return "Unable to unmark the specified task: task index provided is invalid.";
    }

}
