package inu.commons;

/**
 * Represents the ui used to interact with the user.
 */
public class Ui {
    public static void printDivider() {
        System.out.println(Messages.MESSAGE_DIVIDER);
    }

    public static void printGreeting() {
        printDivider();
        System.out.println(Messages.MESSAGE_GREETING);
        System.out.println(Messages.MESSAGE_PROMPT);
        printDivider();
    }

    public static void printFarewell() {
        printDivider();
        System.out.println(Messages.MESSAGE_FAREWELL);
        printDivider();
    }

    public static void printOutput(String output) {
        printDivider();
        System.out.println(output);
        printDivider();
    }
}