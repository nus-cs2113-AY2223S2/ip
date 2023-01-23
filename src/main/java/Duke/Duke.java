package Duke;

import java.util.Scanner;
import Parser.EmptyCommandException;
import Parser.IParser;
import Parser.Parser;

public class Duke {
    static String LINEBREAK = "________________________________________\n";
    static String LINE_TAB_STRING = "    ";
    static String COMMAND_TAB_STRING = "     ";
    private static Scanner sc = new Scanner(System.in);
    private static IParser parser = new Parser(sc);
    /**
     * Main program that runs the Duke program.
     * Greets users and exits.
     * @param args
     */
    public static void main(String[] args) {
        greet();
        try {
            do {
                parser.getNextMessage();
                if (parser.isExit()) {
                    break;
                }
                printSystemMessage(parser.getMessage());
            } while(true);
        } catch (EmptyCommandException e) {
            printSystemMessage("You passed an illegal command!\n\tI will stop here because I am angry Duke");
        }
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
     * Prints message with a tab in front followed by a linebreak.
     * Function used in contrast to printMessage to contrast user command and Duke response
     * @param message Output message to print
     */
    private static void printSystemMessage(String message) {
        System.out.println(LINE_TAB_STRING + LINEBREAK
                        + COMMAND_TAB_STRING + message + '\n'
                        + LINE_TAB_STRING + LINEBREAK);
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

        System.out.println(logo);
        printSystemMessage("Hello! I'm Duke\n     What can I do for you?");
    }
    /**
     * Prints bye messgae to user
     */
    private static void bye() {
        printSystemMessage("Bye. Hope to see you again soon!");
    }
}
