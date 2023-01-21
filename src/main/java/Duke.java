import java.util.Scanner;

public class Duke {
    public static void printDuke() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n");
    }

    public static String breakLine() {
        return "------------------------------------------\n";
    }

    // Greet
    public static void greet() {
        System.out.println(breakLine() + "Hello! I'm Lolo :)\n" + "What can I do for you?\n" + breakLine());
    }

    // echo string
    public static void echo(String text) {
        System.out.print(breakLine() + text + '\n' + breakLine());
    }

    // exit
    public static void exit() {
        System.out.print(breakLine() + "Ba-bye. Hope to see you again soon :)\n" + breakLine());
    }

    // read input
    public static String readInput() {
        System.out.print(">> ");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public static void main(String[] args) {
        printDuke();
        greet();
        String input = readInput();
        while (!input.equals("bye")) {
            echo(input);
            input = readInput();
        }
        exit();
    }

}
