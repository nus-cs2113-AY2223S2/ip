package Duke;

import java.util.Scanner;

public class Duke {
    static String LINEBREAK = "________________________________________\n";
    static Scanner sc = new Scanner(System.in);
    /**
     * Main program that runs the Duke program.
     * Greets users and exits.
     * @param args
     */
    public static void main(String[] args) {
        greet();
        bye();
    }
    /**
     * Prints message followed by a linebreak
     * @param message Output message to print
     */
    private static void printMessage(String message) {
        System.out.println(message);
        System.out.println(LINEBREAK);
    }
    /**
     * Gets the a message from console that user inputs
     * @return String
     */
    private static String getMessage() {
        return sc.nextLine();
    }
    /**
     * Prints greet message to user
     */
    private static void greet() {
        String logo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";

        printMessage(logo);
        printMessage("Hello! I'm Duke\nWhat can I do for you?");
    }
    /**
     * Prints bye messgae to user
     */
    private static void bye() {
        printMessage("Bye. Hope to see you again soon!");
    }
}
