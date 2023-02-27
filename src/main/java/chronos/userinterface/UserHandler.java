package chronos.userinterface;

import chronos.inoutput.Input;

/**
 * The UserHandler class is responsible for getting the user's name and displaying a welcome message
 * with the option to view their To-Do list.
 */
public class UserHandler {
    private static Input inOut;

    /**
     * Constructs a UserHandler object with an Input object.
     * @param inOut the Input object used to get user input
     */
    public UserHandler(Input inOut) {
        this.inOut = inOut;
    }

    /**
     * Prompts the user to enter their name and displays a welcome message
     * with the option to view their To-Do list.
     */
    public void getUserName() {
        System.out.println("What is your name? (Please enter name)\n");
        String userName = inOut.readInput();
        System.out.println("Hello, " + userName + ". You may enter 'list' to view your current To-Do list.");
    }
}
