package exceptions;

public class InvalidTaskException extends Exception{

    /**
     * @return String This returns error call when an error is caught usually
     * when the user enters wrong command and ask the user to re-enter
     */
    public String call(){
        return "Invalid Command! I cannot understand your instruction please re-enter" +
                "\nIf you wish to get help please enter \"help\"";
    }
}
