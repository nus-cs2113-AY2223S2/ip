package tusky.exceptions;

import tusky.constants.Messages;

/**
 * Exception thrown when a key is not found in the body of the user input.
 */
public class KeyNotFoundException extends Exception{
    public KeyNotFoundException(String key){
        super(String.format(Messages.ERR_KEY_NOT_FOUND.toString(), key));
    }
}
