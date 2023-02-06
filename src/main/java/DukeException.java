//Implement a class DukeException that extends the Exception class. 
//This class should be able to handle the different exceptions that may occur in your program.

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