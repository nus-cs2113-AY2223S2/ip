package ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Represent a user input interface.
 */
public class Ui {
    private final String LINE = "____________________________________________________________";
    private final Scanner IN;
    private final String HELLO_MSG = LINE + "\nHello! I'm Duke\nWhat can I do for you?\n" + LINE;
    private final String BYE_MSG = LINE + "\nBye. Hope to see you again soon!\n" + LINE;
    private final String DATA_LOAD_SUCCESS_MSG = LINE + '\n' + "\"./data/duke.txt\" found.\nData loaded into "
            + "Duke!" + '\n' + LINE;
    private final String FILE_CREATED_MSG = LINE + '\n' + "No existing data found.\nCreated new file \"./data/duke"
            + ".txt\"" + '\n' + LINE;

    public Ui(InputStream in) {
        this.IN = new Scanner(in);
    }

    public Ui(File file) throws FileNotFoundException {
        this.IN = new Scanner(file);
    }

    /**
     * Prints the greeting message.
     */
    public void greetUser() {
        System.out.println(HELLO_MSG);
    }

    /**
     * Prints the farewell message.
     */
    public void byeUser() {
        System.out.println(BYE_MSG);
    }

    /**
     * Prints the error message.
     *
     * @param errorMessage The error message to be printed.
     */
    public void printErrorMessage(String errorMessage) {
        System.out.println(LINE + '\n' + errorMessage + '\n' + LINE);
    }

    /**
     * Prints the message that indicates a successful data load.
     */
    public void printDataLoadSuccess() {
        System.out.println(DATA_LOAD_SUCCESS_MSG);
    }

    /**
     * Prints the message that indicates a successful data file creation.
     */
    public void printFileCreated() {
        System.out.println(FILE_CREATED_MSG);
    }

    /**
     * Prints the result of a command.
     *
     * @param result The result of a command.
     */
    public void printCommandResult(String result) {
        if (result == null) {
            return;
        }
        System.out.println(LINE);
        System.out.print(result);
        System.out.println(LINE);
    }

    /**
     * Read in the next line of User Input.
     *
     * @return The next line of User Input.
     */
    public String getNextLineInput() {
        return IN.nextLine();
    }

    /**
     * Check if the User Input has a next line.
     *
     * @return True if there is a next line in the User Input and false otherwise.
     */
    public boolean hasNextLineInput() {
        return IN.hasNextLine();
    }

    /**
     * Close the scanner of UI object.
     */
    public void closeScanner() {
        IN.close();
    }
}
