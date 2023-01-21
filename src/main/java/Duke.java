import java.util.Scanner;

public class Duke {
    // commands
    static final String COMMAND_EXIT = "bye";

    private static void printLine() {
        System.out.println("____________________________________________________________");
    }

    private static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLine();
    }

    private static void greet() {
        System.out.println("Hello! I'm Duke\n"
                + "What can I do for you?");
        printLine();
    }

    private static void exit() {
        System.out.println("Bye. How to see you again soon!");
        printLine();
    }

    private static void echo(String s) {
        System.out.println(s);
        printLine();
    }

    private static int processInput(String s) {
        Scanner input = new Scanner(s);
        String command = input.next();
        if (command.equals(COMMAND_EXIT)) {
            return -1;
        }
        echo(s);
        return 0;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        printLogo();
        greet();

        String input;
        do {
            input = scan.nextLine();
            printLine();
            int state = processInput(input);
            if (state == -1) {
                exit();
                break;
            }
        } while (true);

        scan.close();
    }
}
