package duke.command;

import java.util.Scanner;

/**
 * The <code>Parser</code> object handles the user input and
 * allows the program to understand the commands given by the user.
 */
public class Parser {
    private static Scanner inputScanner = new Scanner(System.in);
    private static String userInput;
    private static String[] userInputList;

    /**
     * Reads the user input and saves it in parts as command and input parameter.
     */
    public static void getUserInput() {
        userInput = inputScanner.nextLine();
        userInputList = userInput.split(" ", 2);
    }

    /**
     * Returns the user command from the user input.
     *
     * @return The user command.
     */
    public static String getUserCommand() {
        return userInputList[0];
    }

    /**
     * Returns the user input parameter from the user input,
     * if no input parameter, an empty string is returned.
     *
     * @return The user input parameter or an empty string.
     */
    public static String getUserInputParameter() {
        if (userInputList.length >= 2) {
            return userInputList[1];
        } else {
            return "";
        }
    }
}
