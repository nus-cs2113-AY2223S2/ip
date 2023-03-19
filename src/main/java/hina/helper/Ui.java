package hina.helper;

import hina.task.Task;

/**
 * Contains methods to print messages to the user.
 */
public class Ui {
    /**
     * Class constructor.
     */
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
    public static void emptyListMessage() {
        System.out.println("There are no items on the list :o");
    }
    public static void taskAdded(Task task) {
        System.out.println("Noted! This task has been added:");
        System.out.println(task);
    }
    public static void saveFound() {
        System.out.println("Saved list found, loading saved list...");
    }
    public static void saveNotFound() {
        System.out.println("Save file not found! Creating new file...");
    }
    public static void saveCreated() {
        System.out.println("Save file created!");
    }
    public static void fileCreateError() {
        System.out.println("T.T Ahh! Something went wrong, could not create file!");
    }
    public static void invalidNumberMessage() {
        System.out.println("That's not a valid number!");
    }
}
