package duke;

import duke.commands.Parser;
import duke.ui.Ui;

import duke.exception.DukeException;

import java.util.Scanner;

/**
 * The main class. Reads in different commands from the user, and parses them accordingly.
 */
public class Duke {
    public static String userInput = "start";

    /**
     * Main method called when the program is running.
     *
     * @param args Not used, should be empty.
     */
    public static void main(String[] args) {
        Ui.printGreetings();
        Scanner in = new Scanner(System.in);
        while (!userInput.equals("bye")) {
            userInput = in.nextLine();
            if (!userInput.equals("bye")) {
                try {
                    Parser.handleUserInputs(userInput);
                } catch (DukeException e) {
                    System.out.println("error");
                }
            }
            Ui.printLine();
        }
        Ui.printExit();
    }
}
