package com.ethanyidong.bunny;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Handles user interaction (input and output from the CLI)
 */
public class BunnyUI {
    private final static String DIVIDER = "____________________________________________________________";
    private final static String GLOBAL_INDENTATION = "\t";
    private final static String MESSAGE_INDENTATION = " ";

    private final Scanner in;

    private boolean isSuppressed;

    public BunnyUI() {
        this.in = new Scanner(System.in);
    }

    /**
     * Prints a message with separators and indentation.
     * The message may have multiple lines, and indentation will be handled accordingly.
     * Does nothing if suppressed.
     * @param message the message to print
     * @see BunnyUI#setIsSuppressed(boolean) 
     */
    public void printMessage(String message) {
        this.printMessage(Arrays.asList(message.split("\n")));
    }

    /**
     * Prints a list of messages in the same section with separators and indentation.
     * Does nothing if suppressed.
     * @param messageLines a list of messages to print
     * @see BunnyUI#setIsSuppressed(boolean)
     */
    public void printMessage(Iterable<String> messageLines) {
        if(this.isSuppressed) {
            return;
        }
        String output = "";
        output += GLOBAL_INDENTATION + DIVIDER + "\n";
        for (String line : messageLines) {
            output += GLOBAL_INDENTATION + MESSAGE_INDENTATION + line + "\n";
        }
        output += GLOBAL_INDENTATION + DIVIDER + "\n";

        System.out.print(output);
    }

    /**
     * Blocks until the user enters a command.
     * @return the next command issued by the user
     */
    public String getNextCommandString() {
        return in.nextLine();
    }

    /**
     * Suppresses or unsuppresses output
     * @param isSuppressed new suppression state
     */
    public void setIsSuppressed(boolean isSuppressed) {
        this.isSuppressed = isSuppressed;
    }
}
