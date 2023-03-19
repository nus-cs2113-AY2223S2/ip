package nova;

import java.util.Scanner;

public class Parser {
    private static Scanner scan = new Scanner(System.in);
    private static String input;
    private static String[] command;

    public static String[] getCommand() {
        input = scan.nextLine();
        command = input.split(" ", 2);
        return command;
    }

}
