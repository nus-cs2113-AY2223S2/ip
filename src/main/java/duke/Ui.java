package duke;

import duke.commands.CommandResult;

import java.util.Scanner;

/**
 * Handles the interactions with the user.
 * Adapted from nus-cs2113-AY2223S2/personbook
 */
public class Ui {

    public static final int DISPLAYED_INDEX_OFFSET = 1;

    private static final String LS = System.lineSeparator();
    static final String DIVIDER = "____________________________________________________________";

    /**
     * Formats the feedback message to the user
     * @param message contains feedback messages of execution
     * Taken from nus-cs2113-AY2223S2/personbook
     */
    public void showToUser(String... message) {
        for (String m : message) {
            System.out.println(m.replace("\n", LS));
        }
    }


    public void showLine() {
        System.out.println(DIVIDER);
    }

    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = DIVIDER
                + "\nHello! I'm Duke\n"
                + "What can I do for you?\n"
                + DIVIDER;
        System.out.println("Hello from\n" + logo + greeting);
    }

    public void farewell() {
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    public String getUserInput() {
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        return userInput;
    }

    /**
     * Formats output message to the user
     * Taken from nus-cs2113-AY2223S2/personbook
     * @param result contains result of the execution of a command
     */
    public void showResultToUser(CommandResult result) {
        showToUser(result.outputToUser, DIVIDER);
    }
}
