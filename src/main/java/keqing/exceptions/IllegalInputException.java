package keqing.exceptions;

public class IllegalInputException extends Exception {
    /**
     * Constructor for IllegalInputException
     * @param message the message to be printed
     */
    public IllegalInputException(String message){
        super(message);
    }
}