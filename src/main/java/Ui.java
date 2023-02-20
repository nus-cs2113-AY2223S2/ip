import java.util.Scanner;

public class Ui {
    private static Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public static String requestUserInput() {
        return scanner.nextLine();
    }

    public static void showGreetingMessage() {
        System.out.println("How might I help you today?");
    }

    public static void showLoadErrorMessage() {
        System.out.println("Error loading file from memory");
    }

    public static void showWelcomeBackMessage() {
        System.out.println("Welcome back!");
        System.out.println("Here are the tasks from last time:");
    }

    public static void showFarewellMessage() {
        System.out.println("Bye hope to see you again soon");
    }
}
