import java.util.Scanner;

public class Orca {
    static final String LOGO = "     _______  ______    _______  _______ \n"
            + "    |       ||    _ |  |       ||   _   | \n"
            + "    |   _   ||   | ||  |       ||  |_|  | \n"
            + "    |  | |  ||   |_||_ |       ||       | \n"
            + "    |  |_|  ||    __  ||      _||       | \n"
            + "    |       ||   |  | ||     |_ |   _   | \n"
            + "    |_______||___|  |_||_______||__| |__| \n";
    static final int MAX_TASKS = 100;
    static final boolean FINISHED = true;

    static Task[] tasks = new Task[MAX_TASKS];
    static int taskCount = 0;
    static Scanner in = new Scanner(System.in);
    static String userInput = "";
    static CommandType commandType;
    static int taskNo;
    static Task newTask;

    public static void printGreetingMessage() {
        System.out.println("    --------------------------------------------------");
        System.out.println(LOGO);
        System.out.println("    Hello! I'm Orca, your assistant chatbot.");
        System.out.println("    What can I do for you?");
        System.out.println("    --------------------------------------------------\n");
    }

    public static void printByeMessage() {
        System.out.println("    --------------------------------------------------");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    --------------------------------------------------\n");
    }

    public static void printUnknownCommandMessage() {
        System.out.println("    --------------------------------------------------");
        System.out.println("    Sorry, I don't understand what you mean.");
        System.out.println("    --------------------------------------------------\n");
    }

    public static void printTasks() {
        System.out.println("    --------------------------------------------------");
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println("    " + (i + 1) + "." + tasks[i]);
        }
        System.out.println("    --------------------------------------------------\n");
    }

    public static void printLatestAddedTask() {
        System.out.println("    --------------------------------------------------");
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + tasks[taskCount - 1]);
        System.out.println("    Now you have " + taskCount + " tasks in the list.");
        System.out.println("    --------------------------------------------------\n");
    }

    public static void findCommandType() {
        if (userInput.equals("bye")) {
            commandType = CommandType.BYE;
        } else if (userInput.equals("list")) {
            commandType = CommandType.LIST;
        } else if (userInput.startsWith("mark")) {
            commandType = CommandType.MARK;
        } else if (userInput.startsWith("unmark")) {
            commandType = CommandType.UNMARK;
        } else if (userInput.startsWith("todo")) {
            commandType = CommandType.TODO;
        } else if (userInput.startsWith("deadline")) {
            commandType = CommandType.DEADLINE;
        } else if (userInput.startsWith("event")) {
            commandType = CommandType.EVENT;
        } else {
            commandType = CommandType.UNKNOWN;
        }
    }

    public static boolean isInputAvailable() {
        return in.hasNextLine();
    }

    public static void readUserInput() {
        userInput = in.nextLine();
    }

    public static void markTask(int taskNo) throws OrcaException {
        try {
            tasks[taskNo - 1].setDone(true);
        } catch (NullPointerException e) {
            throw new OrcaException("There is no task with this number.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new OrcaException("There is no task with this number.");
        }
        System.out.println("    --------------------------------------------------");
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      " + tasks[taskNo - 1]);
        System.out.println("    --------------------------------------------------\n");
    }

    public static void unmarkTask(int taskNo) throws OrcaException {
        try {
            tasks[taskNo - 1].setDone(false);
        } catch (NullPointerException e) {
            throw new OrcaException("There is no task with this number.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new OrcaException("There is no task with this number.");
        }
        System.out.println("    --------------------------------------------------");
        System.out.println("    I've marked this task as not done yet:");
        System.out.println("      " + tasks[taskNo - 1]);
        System.out.println("    --------------------------------------------------\n");
    }

    public static void addTask(Task newTask) {
        tasks[taskCount] = newTask;
        taskCount++;
    }

    public static int parseTaskNo(String userInput, int startIdx) throws OrcaException {
        try {
            return Integer.parseInt(userInput.substring(startIdx));
        } catch (NumberFormatException e) {
            throw new OrcaException("I cannot parse the integer.");
        } catch (StringIndexOutOfBoundsException e) {
            throw new OrcaException("I cannot parse the integer.");
        }
    }

    public static boolean executeCommand() throws OrcaException {
        switch (commandType) {
            case BYE:
                printByeMessage();
                return FINISHED;
            case LIST:
                printTasks();
                break;
            case MARK:
                taskNo = parseTaskNo(userInput, 5);
                markTask(taskNo);
                break;
            case UNMARK:
                taskNo = parseTaskNo(userInput, 7);
                unmarkTask(taskNo);
                break;
            case TODO:
                newTask = new Todo(userInput, 5);
                addTask(newTask);
                printLatestAddedTask();
                break;
            case DEADLINE:
                newTask = new Deadline(userInput, 9);
                addTask(newTask);
                printLatestAddedTask();
                break;
            case EVENT:
                newTask = new Event(userInput, 6);
                addTask(newTask);
                printLatestAddedTask();
                break;
            default:
                throw new OrcaException("Sorry. I can't understand what you mean. :(");
        }
        return !FINISHED;
    }

    public static void runOrca() {
        boolean isFinished = false;
        while (isInputAvailable()) {
            readUserInput();
            findCommandType();
            try {
                isFinished = executeCommand();
            } catch (OrcaException e) {
                System.out.println(e.getMessage());
            }
            if (isFinished) {
                break;
            }
        }
    }

    public static void finishProcess() {
        in.close();
    }

    public static void main(String[] args) {
        printGreetingMessage();
        runOrca();
        finishProcess();
    }
}
