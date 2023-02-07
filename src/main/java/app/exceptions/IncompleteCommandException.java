package app.exceptions;

public class IncompleteCommandException extends DukeException{
    public void printErrorMessage(String commandWord) {
        System.out.printf("ONO! The description of %s cannot be empty.%n", commandWord);
    }
}
