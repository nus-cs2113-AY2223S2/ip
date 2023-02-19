package duke;

import java.util.Scanner;

public class Ui {
    public static final String LINESEPARATOR = "____________________________________________________________";
    private static Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }
    public static void printSeparator() {
        System.out.println(LINESEPARATOR);
    }

    public static String getInput() {
        String input = in.nextLine();
        return input;
    }
    public static void greet() {
        printSeparator();
        System.out.println(" Hello! I'm Duke\n" + " What can I do for you?");
        printSeparator();
        System.out.println("");
    }

    public static void farewell() {
        printSeparator();
        System.out.println(" Bye. Hope to see you again soon!");
        printSeparator();
    }
}
