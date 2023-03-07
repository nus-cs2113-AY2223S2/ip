import java.util.Scanner;

/**
 * Represents ui interaction with user for IO operations.
 */
public class Ui {
    private final String LINE = "    ____________________________________________________________\n";
    private final String LOGO = LINE +
            "     Hello! I'm Duke\n" +
            "     What can I do for you?\n" +
            LINE;
    protected final String SMALL_SPACE = "     ";
    protected final String BIG_SPACE = "       ";

    private Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    /**
     * Reads user inputs.
     *
     * @return user input.
     */
    public String readInput() {
        String input = in.nextLine();
        return input;
    }

    /**
     * Prints Duke logo on start up.
     */
    public void printLogo() {
        this.println(LOGO);
    }

    /**
     * Prints line to separate user input and outputs.
     */
    public void printLine() {
        this.println(LINE);
    }

    /**
     * Prints error message when loading from memory fails.
     */
    public void showLoadingError() {
        this.println("Error loading from memory. Please restart the program.");
    }

    /**
     * Prints success message when loading from memory is successful.
     */
    public void showLoadingSuccess() {
        this.println(LINE);
        this.print(SMALL_SPACE);
        this.println("All Tasks loaded from memory successfully.");
        this.println(LINE);
    }

    /**
     * Prints goodbye when user stops the program.
     */
    public void showGoodbye() {
        this.println(LINE);
        this.println("     " + "Bye. Hope to see you again soon!");
        this.println(LINE);
    }

    /**
     * Prints small indentation space.
     */
    public void printSmallSpace() {
        System.out.print(SMALL_SPACE);
    }

    /**
     * Prints big indentation space.
     */
    public void printBigSpace() {
        System.out.print(BIG_SPACE);
    }

    /**
     * Prints string to user and moves the cursor to a new line.
     */
    public void println(Object string) {
        System.out.println(string);
    }

    /**
     * Prints string to user without moving the cursor to a new line.
     */
    public void print(Object string) {
        System.out.print(string);
    }

}
