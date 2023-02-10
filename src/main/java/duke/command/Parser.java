package duke.command;

import java.util.Scanner;
public class Parser {
    private static Scanner inputScanner = new Scanner(System.in);
    private static String userInput, userCommand, userInputDetails;
    private static String[] userInputList;

    public static void getUserInput() {
        userInput = inputScanner.nextLine();
        userInputList = userInput.split(" ", 2);
    }

    public static String getUserCommand() {
        return userInputList[0];
    }

    public static String getUserInputDetails() {
        if (userInputList.length >= 2) {
            return userInputList[1];
        } else {
            return "";
        }
    }
}
