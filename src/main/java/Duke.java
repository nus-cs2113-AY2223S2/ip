import java.util.Scanner;

public class Duke {
    private enum State {
        SUCCESS, EXIT, INVALID
    }

    static final String INDENT = "    ";

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
                    + tasks[i].describe());
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
        printWithIndentation(tasks[id].describe());
        printLine();
    }

    private static State processInput(String s) {
        Scanner input = new Scanner(s);
        String command = input.next();
        if (command.equals(COMMAND_EXIT)) {
            return State.EXIT;
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

        return State.SUCCESS;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        printLogo();
        greet();

        String input;
        do {
            input = scan.nextLine();
            printLine();
            State result = processInput(input);
            if (result == State.EXIT) {
                exit();
                break;
            }
        } while (true);

        scan.close();
    }
}
