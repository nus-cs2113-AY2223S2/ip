package exceptions;

public class InvalidTaskException extends Exception{
    public String call(){
        return "Invalid Command! I cannot understand your instruction please re-enter";
    }
}
