import java.util.Scanner;

/**
 * Class that deals with interactions with the user
 */
public class Ui {
    private static Scanner scanner;

    /**
     * Constructor for Ui
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads in the next line of user input
     * @return the line of user input
     */
    public static String requestUserInput() {
        return scanner.nextLine();
    }

    /**
     * Prints greetings message on starting Duke
     */
    public static void showGreetingMessage() {
        System.out.println("How might I help you today?");
    }
    /**
     * Prints error message if there is an error loading
     * tasks from the save file
     */
    public static void showLoadErrorMessage() {
        System.out.println("Error loading file from memory");
    }

    /**
     * prints welcome back message if user has prior tasks saved
     */
    public static void showWelcomeBackMessage() {
        System.out.println("Welcome back!");
        System.out.println("Here are the tasks from last time:");
    }

    /**
     * prints farewell message after typing "bye" to Duke
     */
    public static void showFarewellMessage() {
        System.out.println("Bye hope to see you again soon");
    }
}
