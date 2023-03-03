package duke;

import java.util.Scanner;

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

    public void printImpossible() {
        System.out.println("This shouldn't be happening :O");
    }

    public void printNumberFormatException() {
        System.out.println("☹ OOPS!!! Task number should be an integer.");
        printDivider();
    }

    public void printInvalidTaskNumberException() {
        System.out.println("☹ OOPS!!! The task specified does not exist in the task list.");
        printDivider();
    }

    public void printIndexOutOfBoundsException() {
        System.out.println("☹ OOPS!!! The description of 'mark' cannot be empty.");
        printDivider();
    }

    public void printEmptyTaskList() {
        System.out.println("You are free today :)");
    }
}
