package hina.helper;

public class Ui {
    public Ui() {
    }

    public static void showGreeting() {
        System.out.println("Hello master!");
        System.out.println("What are your orders?");
    }
    public static void showExitMessage() {
        System.out.println("Goodbye master, let's meet again soon...");
    }
    public static void invalidCommandMessage() {
        System.out.println(">.< Hina does not recognise this command!");
    }
    public static void notEnoughDetails() {
        System.out.println("@_@ Please give Hina more details!");
    }
    public static void couldNotSaveMessage() {
        System.out.println("Something went wrong, could not save!");
    }
    public static void taskFoundMessage() {
        System.out.println("Found a match! Here are the results master!");
    }
    public static void taskNotFoundMessage() {
        System.out.println("Hina could not find anything about that in master's list...");
    }
    public static void showDateTimeError() {
        System.out.println("Please use dd-MMM-yyyy HH:mm format!");
    }
}
