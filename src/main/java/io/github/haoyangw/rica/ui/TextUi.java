package io.github.haoyangw.rica.ui;

import io.github.haoyangw.rica.exception.RicaException;
import io.github.haoyangw.rica.task.Task;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class TextUi {
    private final Scanner cmdLineIn;
    private final PrintStream cmdLineOut;
    private static final String BYE_PHRASE = " Leaving so soon? Come back anytime, I'll be happy to help!";
    private static final String IMPORTANT_MESSAGE_MARKER = "***";
    private static final String INDENT = "    ";
    private static final String LINE = "____________________________________________________________";

    public TextUi() {
        this.cmdLineIn = new Scanner(System.in);
        this.cmdLineOut = System.out;
    }

    public TextUi(Scanner cmdIn, PrintStream cmdOut) {
        this.cmdLineIn = cmdIn;
        this.cmdLineOut = cmdOut;
    }

    private Scanner getCmdLineIn() {
        return this.cmdLineIn;
    }

    private PrintStream getCmdLineOut() {
        return this.cmdLineOut;
    }

    /**
     * Prints a given runtime Exception on the user's screen using Rica's own output
     *   format.
     *
     * @param exception Exception to inform the user about
     */
    public void printErrorMessage(RicaException exception) {
        this.printlnWithIndent(exception.getMessage());
    }

    /**
     * Prints the footer found at the end of each of Rica's messages.
     */
    public void printFooter() {
        this.printlnWithIndent(TextUi.LINE);
    }

    /**
     * Tells the user that Rica is sad to say goodbye :(
     */
    public void printGoodbyeMessage() {
        this.printlnWithIndent(TextUi.BYE_PHRASE);
    }

    /**
     * Prints the header found at the beginning of each of Rica's messages.
     */
    public void printHeader() {
        this.printlnWithIndent(TextUi.LINE);
    }

    /**
     *
     */
    public void printImportantErrorMessage(RicaException exception) {
        String SUBSTITUTE_FOR_MESSAGE = "%s";
        String messageFormat = TextUi.IMPORTANT_MESSAGE_MARKER + TextUi.INDENT
                + SUBSTITUTE_FOR_MESSAGE + TextUi.INDENT + TextUi.IMPORTANT_MESSAGE_MARKER;
        this.getCmdLineOut().printf(messageFormat, exception.getMessage());
    }

    /**
     * Prints a given message on the user's screen with Rica's own unique indentation.
     *
     * @param message Message to print on the user's screen
     */
    public void printlnWithIndent(String message) {
        this.getCmdLineOut().print(TextUi.INDENT);
        this.getCmdLineOut().println(message);
    }

    /**
     * Prints a list of Tasks remembered by Rica in Rica's unique output format.
     *
     * @param tasks List of Tasks to print on the user's screen
     */
    public void printTasks(List<Task> tasks) {
        this.printTasks(tasks, " I think you have these tasks:",
                " Hope I'm not amnesiac, but I don't remember any tasks?");
    }

    /**
     * Prints a list of Tasks remembered by Rica in Rica's unique output format
     *   with caller-specified messages for when no Tasks are being tracked yet,
     *   and when Rica is currently tracking a list of Tasks.
     *
     * @param tasks List of Tasks to print on the user's screen
     * @param messageToUser Message shown to the user when list of Tasks contains
     *   one or more Tasks
     * @param noTasksMessageToUser Message shown to the user when list of Tasks
     *   is empty
     */
    public void printTasks(List<Task> tasks, String messageToUser, String noTasksMessageToUser) {
        if (tasks.isEmpty()) {
            this.printlnWithIndent(noTasksMessageToUser);
        } else {
            this.printlnWithIndent(messageToUser);
            for (int i = 1; i <= tasks.size(); i += 1) {
                this.printlnWithIndent(" " + i + "." + tasks.get(i - 1));
            }
        }
    }

    /**
     * Prints Rica's lively welcome message shown to the user every time Rica comes
     *   back to life.
     */
    public void printWelcomeMessage() {
        this.printHeader();
        this.printlnWithIndent(" Hello! I'm R.I.C.A.");
        this.printlnWithIndent(" That's Really-Intelligent-Chat-Assistant for you!");
        this.printlnWithIndent(" How may I be of assistance?");
        this.printFooter();
    }

}
