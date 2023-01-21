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
        System.out.println(breakLine()
                + "Hello! I'm Momo :)\n"
                + "What can I do for you?\n"
                + breakLine());
    }

    // reply according to the input
    public static void reply(String text) {
        System.out.print(breakLine()
                + "added: " + text + '\n'
                + breakLine());
    }

    public static void listTask(String[] tasks, int countTasks) {
        System.out.print(breakLine());
        for (int i = 0; i < countTasks; i++) {
            System.out.print((i + 1) + ". " + tasks[i] + '\n');
        }
        System.out.print(breakLine());
    }

    // exit
    public static void exit() {
        System.out.print(breakLine()
                + "Ba-bye. Hope to see you again soon :)\n"
                + breakLine());
    }

    // read input
    public static String readInput() {
        System.out.print(">> ");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public static void main(String[] args) {
        String[] tasks = new String[100];
        int countTasks = 0;

        printDuke();
        greet();
        String input = readInput();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                listTask(tasks, countTasks);
            } else {
                tasks[countTasks] = input;
                countTasks++;
                reply(input);
            }
            input = readInput();
        }
        exit();
    }

}
