package grandduke.exception;

public class OutOfBoundsException extends GrandException {
    @Override
    public String getMessage() {
        return "The index provided is out of bounds. Please try again.";
    }
}
