import java.util.Scanner;

public class Duke {
    static final String INDENT = "    ";
    static final String MARK = "X";

    // commands
    static final String COMMAND_EXIT = "bye";
    static final String COMMAND_LIST = "list";
    static final String COMMAND_MARK = "mark";
    static final String COMMAND_UNMARK = "unmark";

    // data
    static Task[] tasks = new Task[100];
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

    private static void addTask(String taskName) {
        tasks[numTasks] = new Task(taskName);
        ++numTasks;
        printWithIndentation("added: " + taskName);
        printLine();
    }

    private static void listTasks() {
        for (int i = 0; i < numTasks; ++i) {
            printWithIndentation((i + 1) + "." // number
                    + "[" + (tasks[i].getIsCompleted() ? MARK : " ") + "] " // mark
                    + tasks[i].getName()); // name
        }
        printLine();
    }

    private static void setTaskStatus(int id, boolean isCompleted) {
        tasks[id].setIsCompleted(isCompleted);
        if (isCompleted) { // mark task
            printWithIndentation("Nice! I've marked this task as done:\n");
        } else { // unmark task
            printWithIndentation("OK, I've marked this task as not done yet:\n");
        }
        printWithIndentation("[" + (isCompleted ? MARK : " ") + "] " + tasks[id].getName());
        printLine();

    }

    private static int processInput(String s) {
        Scanner input = new Scanner(s);
        String command = input.next();
        if (command.equals(COMMAND_EXIT)) {
            return -1;
        }
        if (command.equals(COMMAND_LIST)) {
            listTasks();
        } else if (command.equals(COMMAND_MARK)) { // mark task
            int number = input.nextInt();
            setTaskStatus(number - 1, true);
        } else if (command.equals(COMMAND_UNMARK)) { // unmark task
            int number = input.nextInt();
            setTaskStatus(number - 1, false);
        } else {
            addTask(s);
        }
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
