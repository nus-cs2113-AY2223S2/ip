package duke;

import java.util.Scanner;

/*
 * This class deals with user interaction
 * displaying text and reading in text
 * */
public class Ui {
    /*
     * a constant used for readability
     */
    private final String LINE_SPACING = "\t____________________________________________________________";
    /*
     * A scanner for reading in user input
     */
    private Scanner scan;

    /*
     * Initialized the scanner in the constructor for user input
     *
     */
    public Ui() {
        scan = new Scanner(System.in);
    }

    /*
     * Displays the line constant in the console
     */
    public void showLine() {
        System.out.println(LINE_SPACING);
    }

    /*
     * Displays the intro message in a formatted way
     */
    public void greetUser() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(LINE_SPACING);
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println(LINE_SPACING);
    }

    /*
     * reads in input
     * @returns String of user input
     */
    public String readCommand() {
        String input = scan.nextLine();
        return input;
    }
}
