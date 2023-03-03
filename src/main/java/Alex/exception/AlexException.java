package Alex.exception;


/**
 *  Specifies AlexException from normal exceptions and allows
 *  categorising into other specific Alex-features exceptions
 */
public class AlexException extends Exception{
    public AlexException(String errorMessage) {
        super(errorMessage);
    }
}
