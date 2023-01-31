import java.util.ArrayList;
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
    static final String COMMAND_TODO = "todo";
    static final String COMMAND_DEADLINE = "deadline";
    static final String COMMAND_EVENT = "event";

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

    private static void addTask(Task taskObj) {
        tasks[numTasks] = taskObj;
        ++numTasks;
        printWithIndentation("added: " + taskObj.getName());
        printWithIndentation("Now you have " + numTasks + " tasks in the list");
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
        String command = input.next().toLowerCase();
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
        } else if (command.equals(COMMAND_TODO)) { // add to do
            // TODO: reduce code repetition
            // validate input
            if (!input.hasNextLine()) {
                return State.INVALID;
            }
            String taskDetails = input.nextLine().trim();
            if (!ToDo.isValidInput(taskDetails)) {
                return State.INVALID;
            }

            // create task
            ArrayList<String> detailsArr = ToDo.convertInputIntoDetails(taskDetails);
            addTask(new ToDo(detailsArr));
        } else if (command.equals(COMMAND_DEADLINE)) { // add deadline
            // validate input
            if (!input.hasNextLine()) {
                return State.INVALID;
            }
            String taskDetails = input.nextLine().trim();
            if (!Deadline.isValidInput(taskDetails)) {
                return State.INVALID;
            }

            // create task
            ArrayList<String> detailsArr = Deadline.convertInputIntoDetails(taskDetails);
            addTask(new Deadline(detailsArr));
        } else if (command.equals(COMMAND_EVENT)) { // add event
            // validate input
            if (!input.hasNextLine()) {
                return State.INVALID;
            }
            String taskDetails = input.nextLine().trim();
            if (!Event.isValidInput(taskDetails)) {
                return State.INVALID;
            }

            // create task
            ArrayList<String> detailsArr = Event.convertInputIntoDetails(taskDetails);
            addTask(new Event(detailsArr));
        } else if (ToDo.isValidInput(s)) {
            addTask(new Task(s));
        } else {
            printWithIndentation("Unrecognised input, try again.");
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
            if (result == State.INVALID) {
                printWithIndentation("Invalid input, try again.");
                printLine();
            }
        } while (true);

        scan.close();
    }
}
