import java.util.Scanner;

public class Duke {
    private static final Scanner scanner = new Scanner(System.in);

    private static void printDivider() {
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printDivider();
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        printDivider();

        // user interaction: echo
        while (true) {
            String msg = scanner.nextLine();
            if (msg.equals("bye")) {
                break;
            }

            printDivider();
            System.out.println("\t" + msg);
            printDivider();
        }

        // exit
        System.out.println("\tBye. Hope to see you again soon!");
        printDivider();
    }
}
