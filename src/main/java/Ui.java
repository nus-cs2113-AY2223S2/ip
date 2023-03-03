/**
 * Represents all user interface
 * that Duke has such as formatting
 * and greeting the user
 */
public class Ui {

    /**
     * Prints a line of dashes for
     * better readability
     */
    public static void printDash() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a welcome message for
     * users when Duke is launched
     */
    public static void printWelcome() {
        printDash();
        System.out.println("Hola! I'm Duke");
        System.out.println("Let me know your tasks for the day!");
        printDash();
    }

    /**
     * Prints an exit message when
     * user intends to exit Duke
     */
    public static void printExit() {
        printDash();
        System.out.println("Bye, cya soon!");
        printDash();
    }
}