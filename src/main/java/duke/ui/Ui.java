package duke.ui;

import duke.command.ExitCommand;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui { // deals with interactions with the user
    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String getUserCommand() { // todo
        return in.nextLine();
    }

    public static final String SEGMENT_LINE = "_".repeat(80);
    public static final String NEW_LINE = System.lineSeparator();
    private static final String START_MESSAGE = " Hello! I'm Duke" + NEW_LINE + " What can I do for you?";
    public static void endLine() {
        System.out.println(SEGMENT_LINE + NEW_LINE);
    }

    public void showStartingError() {
        String output = String.join(NEW_LINE, SEGMENT_LINE, ErrorMessages.ERROR_IN_SETTING_UP.MESSAGE);
        showToUser(output);
    }

    public void greetingMessage() {
        String output = String.join(NEW_LINE, SEGMENT_LINE, START_MESSAGE);
        showToUser(output);
    }

    public void byeMessage() {
        String output = String.join(NEW_LINE, SEGMENT_LINE, ExitCommand.BYE_MESSAGE);
        showToUser(output);
    }

    public void showToUser(String outcome) {
        out.println(outcome);
        endLine();
    }

    public static void showError(String message) {
        System.out.println(SEGMENT_LINE);
        System.out.print(message);
    }

    public static String craftExceedMessage(int taskCount) {
        String output = String.join(Ui.NEW_LINE, SEGMENT_LINE, ErrorMessages.OVER_TASK_COUNT_MESSAGE.MESSAGE);
        output = output.replace("%d", Integer.toString(taskCount));
        return output;
    }
}
