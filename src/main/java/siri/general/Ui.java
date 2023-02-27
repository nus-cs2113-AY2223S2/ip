package siri.general;

import java.util.Scanner;

/**
 * Text UI of the application.
 */
public class Ui {

    /** Logo that shows up at welcome message. */
    private static final String logo = "  ______     __     __  _____     __\n"
            + " | _____|   |__|   |  |/ ____|   |__|\n"
            + " | |____     __    |   /          __\n"
            + " |_____ |   |  |   |  |          |  |\n"
            + " _____| |   |  |   |  |          |  |\n"
            + " |______|   |__|   |__|          |__|\n";

    private static String straightLine = "=====================================================================================";

    private static String siriChatBox = "Siri:\n";

    protected static String userChatBox = "User:\n";

    /** Line that indicates that the user is in the conversation. */
    public void drawUserChatBox (){
        System.out.println(userChatBox);
    }

    /** Line that indicates that "Siri" is in the conversation. */
    public void drawSiriChatBox(){
        System.out.println(siriChatBox);
    }

    /** Straight line that separates "Siri" conversation and the user conversation. */
    public void drawStraightLine(){
        System.out.println(straightLine);
    }

    /** Print welcome messages. */
    public void greet(){
        System.out.println("Hello from \n" + logo);
        System.out.println(straightLine);
        System.out.println(siriChatBox);
        System.out.println("Hey, I'm Siri\n" + "What can I do for you?");
        System.out.println(straightLine);
    }

    /** Print goodbye message. */
    public void sayGoodbye() {
        System.out.println(">o< Goodbye! Hope to see you again soon! >o<");
    }

    /**
     * Prompts for the command and reads the text entered by the user.
     * Return the user input.
     *
     * @return user input entered by the user
     */
    public String readCommand(){
        System.out.println(userChatBox);
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine().trim();
        System.out.println(straightLine);

        return userInput;
    }

    /** Print the loading of files error. */
    public void showLoadingError(){
        System.out.println("The reason you get this error could be: ");
        System.out.println("    1) Unable to write to files.");
        System.out.println("    2) Unable to create files.");
        System.out.println("    3) Unable to update new task list.");
    }

    /** Print error message by "catch" statement. */
    public void showError(String errorMessage){
        System.out.println(errorMessage);
    }
}