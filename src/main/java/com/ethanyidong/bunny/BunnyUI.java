package com.ethanyidong.bunny;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Handles user interaction (input and output from the CLI)
 */
public class BunnyUI {
    public final static String WELCOME_MESSAGE = "Hello! I'm Bunny.\nWhat can I do for you?";
    public final static String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    public final static String ADD_TASK_MESSAGE_FORMAT = "I've added this task: %s\nNow you have %s %s in the list.";
    public final static String DELETE_TASK_MESSAGE_FORMAT =
            "Noted. I've removed this task:\n\t%s\nNow you have %s tasks in the list.";
    public final static String LIST_ELEMENT_MESSAGE_FORMAT = "%s. %s";
    public final static String MARKED_MESSAGE = "Nice! I've marked this task as done";
    public final static String UNMARKED_MESSAGE = "Nice! I've marked this task as not done yet";
    public final static String MARK_MESSAGE_FORMAT = "%s:\n\t%s";

    public final static String LOAD_ERROR_MESSAGE = "Error reading save file! Continuing...";
    public final static String SAVE_ERROR_MESSAGE =
            "Error writing save file! Ensure you have permission to write ./bunny.aof. Disabling save...";
    public final static String INVALID_COMMAND_ERROR_FORMAT = "Invalid command: %s %s";
    public final static String POSITIONAL_ARGUMENT_ERROR = "positional argument";
    public final static String FLAG_ARGUMENT_ERROR_FORMAT = "/%s argument";
    public final static String TASK_INDEX_ARGUMENT_ERROR = "is not a valid task number";
    public final static String MISSING_ARGUMENT_ERROR = "is missing";
    public final static String EMPTY_ARGUMENT_ERROR = "is empty";
    public final static String INTEGER_ARGUMENT_ERROR = "is not a number";

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
     *
     * @param message the message to print
     * @see BunnyUI#setIsSuppressed(boolean)
     */
    public void printMessage(String message) {
        this.printMessage(Arrays.asList(message.split("\n")));
    }

    /**
     * Prints a list of messages in the same section with separators and indentation.
     * Does nothing if suppressed.
     *
     * @param messageLines a list of messages to print
     * @see BunnyUI#setIsSuppressed(boolean)
     */
    public void printMessage(Iterable<String> messageLines) {
        if (this.isSuppressed) {
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
     *
     * @return the next command issued by the user
     */
    public String getNextCommandString() {
        return in.nextLine();
    }

    /**
     * Suppresses or unsuppresses output
     *
     * @param isSuppressed new suppression state
     */
    public void setIsSuppressed(boolean isSuppressed) {
        this.isSuppressed = isSuppressed;
    }
}
