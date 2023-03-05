package buddy.exceptions;

import buddy.messages.Messages;

/**
 * Prints the error message when there is an invalid command inputted by the user
 */
public class InvalidCommandException extends Exception {
    public static void printMessage() {
        System.out.println(Messages.DIVIDER);
        System.out.println("This is an invalid command! Please check and type in again");
        System.out.println(Messages.DIVIDER);
    }

}