package duke.parser;

import java.util.Scanner;

public class Parser {

    public static String entry;
    public static String[] userInput;
    public static Scanner input = new Scanner(System.in);
    public static void readInput() {
        entry = input.nextLine();
    }

    public static String getCommand() {
        readInput();
        userInput = entry.split(" ",2);
        String command = userInput[0];
        return command;
    }

    public static String[] getArguments() {
        if (userInput.length <= 1) {
            return null;
        }
        return userInput;
    }



}
