/**
 * This class represents the exception that is thrown when the user inputs an invalid command.
 * It is a subclass of the Exception class.
 * It contains the printError method to print the error message.
 * @param printError method to print the error message
 */

public class DukeException extends Exception {
    //CommandException
    // Constructor that accepts a message
    public DukeException(String message)
    {
       super(message);
    }
    // Constructor that accepts a message and a cause
    public DukeException(String message, Throwable cause)
    {
       super(message, cause);
    }
    // Constructor that accepts a cause
    public DukeException(Throwable cause)
    {
       super(cause);
    }

    //methods
    public void printError(){
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void printError(String message){
        System.out.println("☹ OOPS!!! " + message);
    }

    public void printError(String message, String command){
        System.out.println("☹ OOPS!!! " + message + " " + command);
    }


}