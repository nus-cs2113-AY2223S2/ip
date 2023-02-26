package tusky.io;

public class KeyNotFoundException extends Exception{
    public KeyNotFoundException(String key){
        super(String.format(Messages.ERR_KEY_NOT_FOUND.toString(), key));
    }
}
