import java.util.Scanner;
public class Duke {
    public static void printDivider() {
        System.out.println("____________________________________________________________");
    }
    public static void printGreeting() {
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
    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
        printDivider();
    }
    public static void echoMessage(String message) {
            printDivider();
            if (message.equals("")) {
                System.out.println("...");
                printDivider();
            }
            System.out.println(message);
    }
    public static void main(String[] args) {

        printGreeting();

        String input;
        Scanner in = new Scanner(System.in);
        String[] tasks = new String[100];
        int taskCounter = 0;
        do {
            input = in.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                break;
            }
            if (input.equalsIgnoreCase("list")) {
                if (taskCounter == 0) {
                    System.out.println("You are free today :)");
                } else {
                    for (int i = 0; i < taskCounter; ++i) {
                        System.out.println(i + 1 + ". " + tasks[i]);
                    }
                }
                printDivider();
            } else {
                tasks[taskCounter] = input;
                ++taskCounter;
                System.out.println("added: " + input);
                printDivider();
            }

        } while (true);

        printBye();
    }
}
