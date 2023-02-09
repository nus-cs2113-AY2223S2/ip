package btb.exceptions;

public class EmptyTaskNumberException extends Exception {
    public String getMessage() {
        return "\t You did not enter a task number (っ °Д °;)っ, please try again!";
    }
}
