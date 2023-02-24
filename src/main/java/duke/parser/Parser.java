package duke.parser;

import java.util.Scanner;

public abstract class Parser {
    public static Scanner inputReader = new Scanner(System.in);

    /* store command and addition inputs argument entered by a user */
    private static String[] inputArgs = null;

    /* read input from a user */
    public static String readInput() {
        System.out.print(">> ");
        return inputReader.nextLine();
    }

    /* split the input to get the command */
    public static String parse() {
        String command;
        String input = readInput();
        inputArgs = input.split(" ", 2);
        command = inputArgs[0];
        return command;
    }

    /* return command argument if any */
    public static String getCommandArgs() {
        if (inputArgs.length <= 1) {
            return null;
        }
        return inputArgs[1];
    }
}
