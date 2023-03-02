package exceptions;

public class IncompleteInputException extends Exception{

    public IncompleteInputException(String inputText) {
        super(inputText);
    }
}
