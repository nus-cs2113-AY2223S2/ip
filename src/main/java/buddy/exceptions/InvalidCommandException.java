package buddy.exceptions;

/**
 * Prints the error message when there is an invalid command inputted by the user
 */
public class InvalidCommandException extends Exception{
    public static void printMessage(){
        System.out.println("This is an invalid command! Please check and type in again");
    }

}