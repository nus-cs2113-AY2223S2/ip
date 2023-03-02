package duke.ui;

import java.util.Scanner;

/**
 * Handles the reading of userInput from the command line.
 */
public class ReadUserInput {

    /**
     * Scans for new user input and parse into String array containing.
     * command and command arguments.
     *
     * @return String array containing command and its arguments.
     */
    public static String[] readInput() {
        Scanner input = new Scanner(System.in);
        String[] userCommand = new String[2];
        userCommand[0] = input.next().toLowerCase();
        userCommand[1] = input.nextLine();
        return userCommand;
    }

}
