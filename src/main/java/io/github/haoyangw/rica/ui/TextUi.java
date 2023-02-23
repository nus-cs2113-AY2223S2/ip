package io.github.haoyangw.rica.ui;

import io.github.haoyangw.rica.exception.RicaException;
import io.github.haoyangw.rica.task.Task;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class TextUi {
    private final Scanner cmdLineIn;
    private final PrintStream cmdLineOut;
    private static final String BYE_PHRASE = " Leaving so soon? Come back anytime, I'll be happy to help!";
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

    public void printErrorMessage(RicaException exception) {
        this.printlnWithIndent(exception.getMessage());
    }

    public void printFooter() {
        this.printlnWithIndent(TextUi.LINE);
    }

    public void printGoodbyeMessage() {
        this.printlnWithIndent(TextUi.BYE_PHRASE);
    }

    public void printHeader() {
        this.printlnWithIndent(TextUi.LINE);
    }

    public void printlnWithIndent(String message) {
        this.getCmdLineOut().print(TextUi.INDENT);
        this.getCmdLineOut().println(message);
    }

    public void printTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            this.printlnWithIndent(" Hope I'm not amnesiac, but I don't remember any tasks?");
        } else {
            this.printlnWithIndent(" I think you have these tasks:");
            for (int i = 1; i <= tasks.size(); i += 1) {
                this.printlnWithIndent(" " + i + "." + tasks.get(i - 1));
            }
        }
    }

    public void printWelcomeMessage() {
        this.printHeader();
        this.printlnWithIndent(" Hello! I'm R.I.C.A.");
        this.printlnWithIndent(" That's Really-Intelligent-Chat-Assistant for you!");
        this.printlnWithIndent(" How may I be of assistance?");
        this.printFooter();
    }

}
