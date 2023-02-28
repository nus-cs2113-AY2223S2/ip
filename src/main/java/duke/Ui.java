package duke;

import java.util.Scanner;

public class Ui {

    public static void printGreet() {
        System.out.println("Hello! I'm duke.");
        System.out.println("What can I do for you?");
    }

    public static void printBye() {
        System.out.println("Bye! Hope to see you again soon!");
    }

    public static String[] getInput() {
        Scanner input = new Scanner(System.in);
        String text = input.nextLine(); // input the whole sentence into text
        return text.split(" ", 2);
    }

    public static void printError(String error) {
        System.out.println(error);
    }
}
