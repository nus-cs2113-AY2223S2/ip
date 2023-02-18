import java.util.Scanner;

/**
 * Represents ui interaction with user for IO operations.
 */
public class Ui {

    private final String LOGO = "    ____________________________________________________________\n" +
            "     Hello! I'm Duke\n" +
            "     What can I do for you?\n" +
            "    ____________________________________________________________\n";



    private final String LINE = "    ____________________________________________________________\n";

    private final String SMALL_SPACE = "     ";
    private final String BIG_SPACE = "       ";

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
        System.out.println(LOGO);
    }

    /**
     * Prints line to separate user input and outputs.
     */
    public void printLine() {
        System.out.println(LINE);
    }

    /**
     * Prints error message when loading from memory fails.
     */
    public void showLoadingError() {
        System.out.println("Error loading from memory. Please restart the program.");
    }

    /**
     * Prints success message when loading from memory is successful.
     */
    public void showLoadingSuccess() {
        System.out.println(LINE);
        System.out.print(SMALL_SPACE);
        System.out.println("All Tasks loaded from memory successfully.");
        System.out.println(LINE);
    }

    /**
     * Prints goodbye when user stops the program.
     */
    public void showGoodbye() {
        System.out.println(LINE);
        System.out.println("     " + "Bye. Hope to see you again soon!");
        System.out.println(LINE);
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
     * Prints string to user.
     */
    public void print(String string) {
        System.out.println(string);
    }

}
