package duke;

import duke.exceptions.InvalidInputException;
import duke.exceptions.InvalidTaskFormatException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskEnum;
import duke.tasks.ToDo;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String INDENT = "    ";
    private static final String LINE = "____________________________________________________________";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String CHAR_SPACE = " ";

    // commands
    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";

    // messages
    private static final String MESSAGE_COMMAND_UNRECOGNISED = "Unrecognised command, try again.";
    private static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";
    private static final String MESSAGE_GREET = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String MESSAGE_LOGO = "Hello from";
    private static final String MESSAGE_TASKS_AVAILABLE = "Here are the tasks in your list:";
    private static final String MESSAGE_TASKS_INVALID_ID = "Invalid task ID entered.";
    private static final String MESSAGE_TASKS_MARKED = "Nice! I've marked this task as done:";
    private static final String MESSAGE_TASKS_NONE = "There are no tasks available.";
    private static final String MESSAGE_TASKS_UNMARKED = "OK, I've marked this task as not done yet:";

    // data
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        printLogo();
        greet();

        String input;
        do {
            input = scan.nextLine();
            boolean isExit = input.split(CHAR_SPACE)[0].equals(COMMAND_EXIT);
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
                throw new InvalidInputException(MESSAGE_COMMAND_UNRECOGNISED);
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
        printWithIndentation(LINE + "\n");
    }

    private static void printLogo() {
        printWithIndentation(MESSAGE_LOGO + "\n" + LOGO);
        printLine();
    }

    private static void greet() {
        printWithIndentation(MESSAGE_GREET);
        printLine();
    }

    private static void addTask(Task taskObj) {
        tasks.add(taskObj);
        String output = "Got it. I've added this task:\n"
                + INDENT + taskObj.describe() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list";
        printWithIndentation(output);
        printLine();
    }

    private static void setTaskStatus(int id, boolean isCompleted) throws InvalidInputException {
        try {
            if (id >= tasks.size() || id < 0) {
                throw new IndexOutOfBoundsException();
            }
            tasks.get(id).setIsCompleted(isCompleted);
            String output = isCompleted
                            ? MESSAGE_TASKS_MARKED + "\n"
                            : MESSAGE_TASKS_UNMARKED + "\n";
            output += tasks.get(id).describe();
            printWithIndentation(output);
            printLine();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException(tasks.size() == 0
                                            ? MESSAGE_TASKS_NONE
                                            : MESSAGE_TASKS_INVALID_ID);
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
        printWithIndentation(MESSAGE_EXIT);
        printLine();
    }

    private static void handleCommandList() {
        String output = tasks.size() == 0
                        ? MESSAGE_TASKS_NONE
                        : MESSAGE_TASKS_AVAILABLE + "\n";
        // adds tasks to output, if any
        // combine details of tasks into a single string
        for (int i = 0; i < tasks.size(); ++i) {
            output += (i + 1) + "." // number
                    + tasks.get(i).describe() + "\n";
        }
        printWithIndentation(output);
        printLine();
    }

    private static void handleCommandMark(Scanner input) throws InvalidInputException {
        if (!input.hasNextInt()) {
            throw new InvalidInputException(MESSAGE_TASKS_INVALID_ID);
        }
        int taskNumber = input.nextInt();
        setTaskStatus(taskNumber - 1, true);
    }

    private static void handleCommandUnmark(Scanner input) throws InvalidInputException {
        if (!input.hasNextInt()) {
            throw new InvalidInputException(MESSAGE_TASKS_INVALID_ID);
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
