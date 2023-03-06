package duke.ui;

import duke.command.ExitCommand;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * deals with interactions with the user
 */
public class Ui {

    public static final String SEGMENT_LINE = "_".repeat(80);

    /**
     * platform independent line separator
     */
    public static final String NEW_LINE = System.lineSeparator();

    private static final String START_MESSAGE = " Hello! I'm Duke" + NEW_LINE + " What can I do for you?";

    /**
     * a separator to be used after every full output
     */
    public static void endLine() {
        System.out.println(SEGMENT_LINE + NEW_LINE);
    }

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Reads the text entered by the user
     *
     * @return the string of text entered by the user
     */
    public String getUserCommand() {
        String input = in.nextLine();
        while (input.trim().isEmpty()) {
            input = in.nextLine();
        }
        return input;
    }

    /**
     * Generates and prints the output to be shown when the program faces an error on start up
     */
    public void showStartingError() {
        String output = String.join(NEW_LINE, SEGMENT_LINE, ErrorMessages.ERROR_IN_SETTING_UP.MESSAGE);
        showToUser(output);
    }

    /**
     * Generates and prints the greeting message on start up
     */
    public void greetingMessage() {
        String output = String.join(NEW_LINE, SEGMENT_LINE, START_MESSAGE);
        showToUser(output);
    }

    /**
     * Generates and prints the greeting message on start up
     */
    public void byeMessage() {
        String output = String.join(NEW_LINE, SEGMENT_LINE, ExitCommand.BYE_MESSAGE);
        showToUser(output);
    }

    /**
     * Shows the user the message
     *
     * @param message string describing the output to be shown to user
     */
    public void showToUser(String message) {
        out.println(message);
        endLine();
    }

    /**
     * Shows the user the error message
     *
     * @param message an error message to be shown to user
     */
    public static void showError(String message) {
        System.out.println(SEGMENT_LINE);
        System.out.print(message);
    }

    /**
     * Generates the error message when the number provided by user is > the number of tasks present
     *
     * @param taskCount the number of tasks present in the program
     * @return the message stating task count exceeded and the number of tasks present
     */
    public static String craftExceedMessage(int taskCount) {
        String output = String.join(Ui.NEW_LINE, SEGMENT_LINE, ErrorMessages.OVER_TASK_COUNT_MESSAGE.MESSAGE);
        output = output.replace("%d", Integer.toString(taskCount));
        return output;
    }
}
