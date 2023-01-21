import java.util.Scanner;

public class Duke {
    static final String INDENT = "    ";
    // commands
    static final String COMMAND_EXIT = "bye";

    // data
    static String[] tasks = new String[100];
    static int numTasks = 0;

    private static void printWithIndentation(String s) {
        Scanner scan = new Scanner(s);
        while (scan.hasNextLine()) {
            System.out.println(INDENT + scan.nextLine());
        }
        scan.close();
    }

    private static void printLine() {
        printWithIndentation("____________________________________________________________\n");
    }

    private static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        printWithIndentation("Hello from\n" + logo);
        printLine();
    }

    private static void greet() {
        printWithIndentation("Hello! I'm Duke\n"
                + "What can I do for you?");
        printLine();
    }

    private static void exit() {
        printWithIndentation("Bye. How to see you again soon!");
        printLine();
    }

    private static void addTask(String task) {
        tasks[numTasks] = task;
        ++numTasks;
        printWithIndentation("added: " + task);
        printLine();
    }

    private static int processInput(String s) {
        Scanner input = new Scanner(s);
        String command = input.next();
        if (command.equals(COMMAND_EXIT)) {
            return -1;
        }
        addTask(s);
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
