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

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        printLogo();
        greet();

        String input;
        boolean programRunning = true;
        do {
            input = scan.nextLine();
            printLine();
            State result = processInput(input);
            switch (result) {
            case EXIT:
                handleStateExit();
                programRunning = false;
                break;
            case INVALID:
                handleStateInvalid();
                break;
            }
        } while (programRunning);

        scan.close();
    }

    private static State processInput(String s) {
        Scanner input = new Scanner(s);
        String command = input.next().toLowerCase();
        if (command.equals(COMMAND_EXIT)) {
            return State.EXIT;
        }

        State finalState;
        switch (command) {
        case COMMAND_EXIT:
            finalState = State.EXIT;
            break;
        case COMMAND_LIST:
            finalState = handleCommandList();
            break;
        case COMMAND_MARK:
            finalState = handleCommandMark(input);
            break;
        case COMMAND_UNMARK:
            finalState = handleCommandUnmark(input);
            break;
        case COMMAND_TODO:
            finalState = handleCommandTodo(input);
            break;
        case COMMAND_DEADLINE:
            finalState = handleCommandDeadline(input);
            break;
        case COMMAND_EVENT:
            finalState = handleCommandEvent(input);
            break;
        default:
            printWithIndentation("Unrecognised command, try again.");
            finalState = State.INVALID;
        }

        return finalState;
    }

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

    private static void addTask(Task taskObj) {
        tasks[numTasks] = taskObj;
        ++numTasks;
        String output = "Got it. I've added this task:\n"
                + INDENT + taskObj.describe() + "\n"
                + "Now you have " + numTasks + " tasks in the list";
        printWithIndentation(output);
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

    private static String getTaskDetails(Scanner input) {
        // validate input
        if (!input.hasNextLine()) {
            return "";
        }
        String taskDetails = input.nextLine().trim();
        if (!ToDo.isValidInput(taskDetails)) {
            return "";
        }
        return taskDetails;
    }

    private static void handleStateExit() {
        printWithIndentation("Bye. Hope to see you again soon!");
        printLine();
    }

    private static void handleStateInvalid() {
        printWithIndentation("Invalid input, try again.");
        printLine();
    }

    private static State handleCommandList() {
        String output = "Here are the tasks in your list:\n";
        for (int i = 0; i < numTasks; ++i) {
            output += (i + 1) + "." // number
                    + tasks[i].describe() + "\n";
        }
        printWithIndentation(output);
        printLine();
        return State.SUCCESS;
    }

    private static State handleCommandMark(Scanner input) {
        int number = input.nextInt();
        setTaskStatus(number - 1, true);
        return State.SUCCESS;
    }

    private static State handleCommandUnmark(Scanner input) {
        int number = input.nextInt();
        setTaskStatus(number - 1, false);
        return State.SUCCESS;
    }

    private static State handleCommandTodo(Scanner input) {
        String taskDetails = getTaskDetails(input);
        if (taskDetails.equals("")) {
            return State.INVALID;
        }

        // create task
        ArrayList<String> detailsArr = ToDo.convertInputIntoDetails(taskDetails);
        addTask(new ToDo(detailsArr));
        return State.SUCCESS;
    }

    private static State handleCommandDeadline(Scanner input) {
        String taskDetails = getTaskDetails(input);
        if (taskDetails.equals("")) {
            return State.INVALID;
        }

        // create task
        ArrayList<String> detailsArr = Deadline.convertInputIntoDetails(taskDetails);
        addTask(new Deadline(detailsArr));
        return State.SUCCESS;
    }

    private static State handleCommandEvent(Scanner input) {
        String taskDetails = getTaskDetails(input);
        if (taskDetails.equals("")) {
            return State.INVALID;
        }

        // create task
        ArrayList<String> detailsArr = Event.convertInputIntoDetails(taskDetails);
        addTask(new Event(detailsArr));
        return State.SUCCESS;
    }
}
