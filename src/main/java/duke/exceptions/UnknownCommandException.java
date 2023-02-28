package duke.exceptions;

public class UnknownCommandException extends Exception {
    public void printErrorMessage() {
        System.out.println("OOPS!! I'm sorry but I don't know what that means :-(");
    }
}
