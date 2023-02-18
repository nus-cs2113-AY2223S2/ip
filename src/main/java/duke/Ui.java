package duke;

public class Ui {

    /**
     * Displays the divider line.
     */
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the welcome message to the user.
     */
    public static void printWelcomeMessage() {
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
    }

    /**
     * Displays the exit message to the user.
     */
    public static void printByeMessage() {
        printLine();
        System.out.println("Bye. Hope to see you again soon! :)");
        printLine();
    }

}
