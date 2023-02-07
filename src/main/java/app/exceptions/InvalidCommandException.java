package app.exceptions;

public class InvalidCommandException extends DukeException{

    public void printErrorMessage(String commandWord) {
        System.out.println("ONO! Please enter a valid command.");
    }
}
