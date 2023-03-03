package siri.exception;

/**
 * Signals an error caused by entering command that is not a "default" command in the application.
 */
public class UnknownCommandException extends Exception {
    public String printError(){
        String errorMessage = "T^T OPPS!!! I'm sorry, but I don't know what that means";
        return errorMessage;
    }
}
