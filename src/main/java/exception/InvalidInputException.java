package exception;

public class InvalidInputException extends Exception{
    public InvalidInputException (String desc) {
        super(desc);
    }
}
