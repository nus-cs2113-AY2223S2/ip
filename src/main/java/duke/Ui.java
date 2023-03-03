package duke;

import java.util.Scanner;

/**
 * This class contains the UI for the CLI.
 */
public class Ui {

    public String getUserInput() {
        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();

        return input;
    }
    public void printDivider() {
        System.out.println("____________________________________________________________");
    }

    public void printStartMessage() {
        String logo = " ____\n"
                + "|    \\ ___ _ _ ___\n"
                + "|  |  | .'| | | -_|\n"
                + "|____/|__,|\\_/|___|\n";

        printDivider();
        System.out.print(logo);
        System.out.println("Hi, I'm Dave!\n"
                + "What can I do for you?");
        printDivider();
    }

    public void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
        printDivider();
    }
}
