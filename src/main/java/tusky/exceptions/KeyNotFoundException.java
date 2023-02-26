package tusky.exceptions;

import tusky.constants.Messages;

public class KeyNotFoundException extends Exception{
    public KeyNotFoundException(String key){
        super(String.format(Messages.ERR_KEY_NOT_FOUND.toString(), key));
    }
}
