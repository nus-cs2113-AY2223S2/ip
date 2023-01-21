import java.util.Scanner;

public class Duke {

    static final String HORIZONTAL_LINE = "____________________________________________________________";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String userInput;

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);

        userInput = scanner.nextLine();

        while (!userInput.equals("bye")) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println(userInput);
            System.out.println(HORIZONTAL_LINE);
            System.out.println();
            userInput = scanner.nextLine();
        }

        exit();

    }

    public static void exit() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }
}