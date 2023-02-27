package duke.command;

/**
 * The <code>Ui</code> object represents the user interface
 * that interacts with the user by producing output.
 */
public class Ui {
    public static final String HORIZONTAL_LINE = "____________________________________________________________";

    public static void logo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo + "\n");
    }

    /**
     * Prints a horizontal line.
     */
    public static void line() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints the welcome message.
     */
    public static void hello() {
        Ui.logo();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Prints the closing message.
     */
    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Echos the user input by printing it back to the terminal.
     *
     * @param userInput The user input that will be echoed.
     */
    public static void echo(String userInput) {
        Ui.line();
        System.out.println(userInput);
        Ui.line();
    }

    /**
     * Prints a message to inform the user that they have input an invalid command.
     */
    public static void unknownCommandHandler() {
        Ui.line();
        System.out.println("You have entered an unknown command! Please try again!");
        Ui.line();
    }
}
