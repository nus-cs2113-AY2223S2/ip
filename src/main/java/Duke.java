import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        final String HORIZONTAL_LINE = "____________________________________________________________";

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE + '\n');

        Scanner sc = new Scanner(System.in);

        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                break;
            }
            System.out.println(HORIZONTAL_LINE);
            System.out.println(userInput);
            System.out.println(HORIZONTAL_LINE + '\n');
        }

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE + '\n');
    }
}
