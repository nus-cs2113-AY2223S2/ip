package Arsdorint.UI;

import Arsdorint.MessageList;
import Arsdorint.command.CommandRes;

import java.io.PrintStream;
import java.util.Scanner;

import static Arsdorint.Arsdorint.logo;
import static Arsdorint.MessageList.*;

/**
 * Represent the class taking in user input and display command output
 */
public class TextUI {
    public final Scanner input;
    public final PrintStream output;
    /**
     * Offset 0 index to 1
     */
    public static final int INDEX_OFFSET = 1;
    /**
     * Format of indexed list item
     */
    private static final String INDEX_LIST_ITEM_FORMAT = "\t%1$d. %2$s";
    /**
     * User Prompt
     */
    public static final String UI_PROMPT = ">>> ";

    public TextUI() {
        this.input = new Scanner(System.in);
        this.output = System.out;
    }

    /**
     * Get user command and filter blank lines
     */
    public String getUserCommand() {
        output.print(UI_PROMPT);
        String userInput = input.nextLine();
        while (shouldIgnore(userInput)) {
            userInput = input.nextLine();
        }
        return userInput;
    }

    /**
     * Ignore if there is an empty line
     */
    public boolean shouldIgnore(String input) {
        return input.trim().isEmpty();
    }

    /**
     * Display multiple messages, separated by newline
     *
     * @param messages String array of message need to be displayed.
     */
    public static void showToUser(String... messages) {
        for (String m : messages) {
            System.out.println(m);
        }
    }

    /**
     * Show the message and the tasks affected by the command
     *
     * @param res Result of a command
     */
    public void showResToUser(CommandRes res) {
        output.println(MessageList.MESSAGE_DIVIDER);
        showToUser(res.messageTop);
        showToUser(res.printTask());
        showToUser(res.messageBottom);
        output.println(MessageList.MESSAGE_DIVIDER);
    }

    /**
     * Show the message when the user type "exit" command
     *
     */
    public void showExitMessage() {
        showToUser(MESSAGE_DIVIDER, EXIT_MESSAGE, MESSAGE_DIVIDER);
    }

    public void showUnknownMessage() {
        output.println(MessageList.MESSAGE_DIVIDER);
        this.showToUser(MessageList.COMMAND_LIST_MESSAGE);
        output.println(MessageList.MESSAGE_DIVIDER);
    }

    /**
     * Show the welcome message from Arsdorint Team and the command list instruction to the user
     *
     */
    public void showHelloMessage() {
        System.out.println("Hello from\n" + logo);
        showToUser(MESSAGE_DIVIDER, HELLO_MESSAGE, COMMAND_LIST_MESSAGE, QUESTION, MESSAGE_DIVIDER);
    }
}
