package exceptions;

/**
 * When event command does not contain the '/from' and '/to' keyword, throw this exception.
 */
public class EventParamsFormatException extends Exception{
    public EventParamsFormatException(){

    }
}
