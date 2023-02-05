package io;

public class KeyNotFoundException extends Exception{
    public KeyNotFoundException(String key){
        super(String.format(Message.ERR_KEY_NOT_FOUND.toString(), key));
    }
}
