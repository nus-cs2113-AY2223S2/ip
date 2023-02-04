import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String INDENT = "    ";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    // commands
    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";

    // data
    private static Task[] tasks = new Task[100];
    private static int numTasks = 0;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        printLogo();
        greet();

        String input;
        do {
            input = scan.nextLine();
            boolean isExit = input.split(" ")[0].equals(COMMAND_EXIT);
            if (isExit) {
                printLine();
                handleStateExit();
                break;
            }
            if (!input.isEmpty()) {
                printLine();
                executeInputCommand(input);
            }
        } while (true);

        scan.close();
    }

    private static void executeInputCommand(String s) {
        Scanner input = new Scanner(s);
        String command = input.next().toLowerCase();

        try {
            switch (command) {
            case COMMAND_LIST:
                handleCommandList();
                break;
            case COMMAND_MARK:
                handleCommandMark(input);
                break;
            case COMMAND_UNMARK:
                handleCommandUnmark(input);
                break;
            case COMMAND_TODO:
                handleCommandTodo(input);
                break;
            case COMMAND_DEADLINE:
                handleCommandDeadline(input);
                break;
            case COMMAND_EVENT:
                handleCommandEvent(input);
                break;
            default:
                throw new InvalidInputException("Unrecognised command, try again.");
            }
        } catch (Exception e) {
            printWithIndentation(e.getMessage());
            printLine();
        }
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
        printWithIndentation("Hello from\n" + LOGO);
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

    private static void setTaskStatus(int id, boolean isCompleted) throws InvalidInputException {
        try {
            if (id >= numTasks || id < 0) {
                throw new IndexOutOfBoundsException();
            }
            tasks[id].setIsCompleted(isCompleted);
            String output = isCompleted
                            ? "Nice! I've marked this task as done:\n"
                            : "OK, I've marked this task as not done yet:\n";
            output += tasks[id].describe();
            printWithIndentation(output);
            printLine();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException(numTasks == 0
                                            ? "There are no tasks available."
                                            : "Invalid task ID entered.");
        }
    }


    private static String getTaskDetails(Scanner input) {
        // validate input
        if (!input.hasNextLine()) {
            return "";
        }
        String taskDetails = input.nextLine().trim();
        return ToDo.isValidInput(taskDetails)
               ? taskDetails
               : "";
    }

    private static void handleStateExit() {
        printWithIndentation("Bye. Hope to see you again soon!");
        printLine();
    }

    private static void handleCommandList() {
        String output = numTasks == 0
                        ? "There are no tasks available."
                        : "Here are the tasks in your list:\n";
        // adds tasks to output, if any
        // combine details of tasks into a single string
        for (int i = 0; i < numTasks; ++i) {
            output += (i + 1)
                    + "." // number
                    + tasks[i].describe() + "\n";
        }
        printWithIndentation(output);
        printLine();
    }

    private static void handleCommandMark(Scanner input) throws InvalidInputException {
        if (!input.hasNextInt()) {
            throw new InvalidInputException("Invalid task ID entered.");
        }
        int taskNumber = input.nextInt();
        setTaskStatus(taskNumber - 1, true);
    }

    private static void handleCommandUnmark(Scanner input) throws InvalidInputException {
        if (!input.hasNextInt()) {
            throw new InvalidInputException("Invalid task ID entered.");
        }
        int taskNumber = input.nextInt();
        setTaskStatus(taskNumber - 1, false);
    }

    private static void handleCommandTodo(Scanner input) throws InvalidTaskFormatException {
        // check if input matches required specifications
        String taskDetails = getTaskDetails(input);
        if (taskDetails.equals("")) {
            throw new InvalidTaskFormatException(TaskEnum.TODO);
        }

        // create task
        ArrayList<String> detailsArr = ToDo.convertInputIntoDetails(taskDetails);
        addTask(new ToDo(detailsArr));
    }

    private static void handleCommandDeadline(Scanner input) throws InvalidTaskFormatException {
        // check if input matches required specifications
        String taskDetails = getTaskDetails(input);
        if (taskDetails.equals("")) {
            throw new InvalidTaskFormatException(TaskEnum.DEADLINE);
        }

        // create task
        ArrayList<String> detailsArr = Deadline.convertInputIntoDetails(taskDetails);
        addTask(new Deadline(detailsArr));
    }

    private static void handleCommandEvent(Scanner input) throws InvalidTaskFormatException {
        // check if input matches required specifications
        String taskDetails = getTaskDetails(input);
        if (taskDetails.equals("")) {
            throw new InvalidTaskFormatException(TaskEnum.EVENT);
        }

        // create task
        ArrayList<String> detailsArr = Event.convertInputIntoDetails(taskDetails);
        addTask(new Event(detailsArr));
    }
}
