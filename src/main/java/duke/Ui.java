package duke;

import command.Command;
import exception.CommandNotRecognisedException;
import exception.IllegalCharacterException;

import java.util.Scanner;

public class Ui {


    public static String getUserInput() {
        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();

        return input;
    }
    public static void printDivider() {
        System.out.println("____________________________________________________________");
    }

    public static void printStartMessage() {
        String logo = " ____\n"
                + "|    \\ ___ _ _ ___\n"
                + "|  |  | .'| | | -_|\n"
                + "|____/|__,|\\_/|___|\n";

        printDivider();
        System.out.println("List Summary:");
        Duke.printTaskList();
        System.out.print(logo);
        System.out.println("Hi, I'm Dave!\n"
                + "What can I do for you?");
        printDivider();
    }

    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
        Ui.printDivider();
    }
}
