package duke;

import java.util.Scanner;

public class Ui {
    private static final Scanner in = new Scanner(System.in);
    public static void printWelcomeMessage() {
        String welcomeMessage = "____________________________________________________________\n" +
                " Hello! I'm Elzi, your dog!\n" +
                " What can I do for my master?\n" +
                "____________________________________________________________\n";
        System.out.println(welcomeMessage);
    }

    public static String getCommand() {
        System.out.println("What is my task master?");
        return in.nextLine();
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println("Oops! I encountered this error! "+ errorMessage);
    }

    public static void printLine() {
        System.out.println("-------------------------------------------------");
    }

    public static void printByeMessage() {
        System.out.println("Good bye! See you soon!");

    }

}
