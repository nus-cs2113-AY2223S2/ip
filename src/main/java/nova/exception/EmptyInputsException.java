package nova.exception;

public class EmptyInputsException extends Exception {
    @Override
    public String getMessage() {
        return "One of your inputs is empty. Please try again";
    }
}