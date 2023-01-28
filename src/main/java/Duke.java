import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        echo();
    }

    public static void greet() {
        String greeting = "____________________________________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n"
                + "\n";
                System.out.println(greeting);
    }

    public static String getUserInput() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        return input;
    }

    public static void echo() {
        for (int x = 0; x < 100; x += 1) {
            String command = getUserInput();
            System.out.println("____________________________________________________________\n");
            if (command.compareTo("bye") == 0) {
                System.out.println("Bye. Hope to see you again soon!\n" + "____________________________________________________________\n");
                break;
            } else {
                System.out.println(command + "\n" + "____________________________________________________________\n");
            }
        }
    }
}
