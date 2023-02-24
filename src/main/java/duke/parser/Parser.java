package duke.parser;

import java.util.Scanner;

public abstract class Parser {
    public static Scanner inputReader = new Scanner(System.in);
    private static String[] inputArgs = null;

    // read input
    public static String readInput() {
        System.out.print(">> ");
        return inputReader.nextLine();
    }

    public static String parse() {
        String command;
        String input = readInput();
        inputArgs = input.split(" ", 2);
        command = inputArgs[0];
        return command;
    }

    public static String getCommandArgs() {
        if (inputArgs.length <= 1) {
            return null;
        }
        return inputArgs[1];
    }
}
