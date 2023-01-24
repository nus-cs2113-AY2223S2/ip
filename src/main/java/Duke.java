import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String divider = "____________________________________________________________";

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(divider);
        System.out.println("Hello from\n" + logo);

        System.out.println(divider);
        System.out.println("Hello! I'm Duke\n"
                         + "What can I do for you?");
        System.out.println(divider);

        String input;
        Scanner in = new Scanner(System.in);
        do {
            input =in.nextLine();

            if (input.toLowerCase().equals("bye")) {
                break;
            }
            System.out.println(divider);
            if (input.equals("")) {
                System.out.println("Please enter a message to echo or 'bye' to exit.");
                System.out.println(divider);
            }
            System.out.println(input);
        } while (true);

        System.out.println(divider);
        System.out.println("Bye. Hope to see you again soon!");

        System.out.println(divider);
    }
}
