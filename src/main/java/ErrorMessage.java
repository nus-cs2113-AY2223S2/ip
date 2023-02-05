public enum ErrorMessage {
    INVALID_COMMAND("I am sorry, I do not understand what this command means"),
    MISSING_TODO_PARAMETER("I am sorry, you are missing something");
    private final String error;

    ErrorMessage(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return error;
    }
}
