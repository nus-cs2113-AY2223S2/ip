import java.util.Scanner;

public class Duke {
    private static final int MAX_ITEMS = 100;
    private static final String TOP_DIVIDER = "============================================================\n";
    private static final String BOTTOM_DIVIDER = "\n============================================================\n\n";
    private static final String ADD_TASK_ITEM_DESC = " Roger. I've added this task:\n  ";
    private static final String LIST_TASK_ITEM_DESC = " Here are the tasks in your list:\n";
    private static final String COMMAND_MARK_TASK = "mark";
    private static final String COMMAND_UNMARK_TASK = "unmark";
    private static final String MARK_TASK_DESC = " Good Job! I've marked this task as completed:\n  ";
    private static final String UNMARK_TASK_DESC = " Noted, I have marked this task as incomplete:\n  ";
    private static final String WHITESPACE = " ";
    private static final String DOT = ".";
    private static final String LIST_TASK_ITEM_BOTTOM_DIVIDER
            = "============================================================\n\n";
    private static final String WELCOME_MESSAGE = " Hello! I'm Chatty\n How can I help you?";
    private static final String COMMAND_LIST_TASK_ITEM = "list";
    private static final String COMMAND_EXIT = "bye";
    private static final String EXIT_DESC = " Goodbye. Hope to see you again soon!";
    private static final String CUR_NO_OF_TASK_START_DESC = "\n You currently have ";
    private static final String CUR_NO_OF_TASK_END_DESC = " task(s) in the list.";
    private static final String COMMAND_ADD_TODO = "todo";
    private static final String COMMAND_ADD_DEADLINE = "deadline";
    private static final String COMMAND_ADD_EVENT = "event";
    private static final String UNKNOWN_CMD_ERR = " WHOOPS! I'm sorry, but I don't know what that means :(";
    private static final String EMPTY_TODO_DESC_ERR = " WHOOPS! The description of a todo cannot be empty.";

    private static Task[] taskItems = new Task[MAX_ITEMS];
    private static int taskItemCount = 0;

    public static void printAddTaskItems() {
        String printAddItem = TOP_DIVIDER + ADD_TASK_ITEM_DESC + taskItems[taskItemCount]
                + CUR_NO_OF_TASK_START_DESC + (taskItemCount + 1) + CUR_NO_OF_TASK_END_DESC + BOTTOM_DIVIDER;
        System.out.print(printAddItem);
    }

    public static void addDeadlineTaskItems(String taskName, String by) {
        taskItems[taskItemCount] = new Deadline(taskName, by);
        printAddTaskItems();
        taskItemCount++;
    }

    public static void addEventTaskItems(String taskName, String from, String to) {
        taskItems[taskItemCount] = new Event(taskName, from, to);
        printAddTaskItems();
        taskItemCount++;
    }

    public static void addTodoTaskItems(String taskName) {
        taskItems[taskItemCount] = new Todo(taskName);
        printAddTaskItems();
        taskItemCount++;
    }

    public static void markTaskItems(int taskItemNo, String command) {
        if (command.equals(COMMAND_MARK_TASK)) {
            taskItems[taskItemNo].setCompleted();
            String printTaskCompleted = TOP_DIVIDER + MARK_TASK_DESC + taskItems[taskItemNo] + BOTTOM_DIVIDER;
            System.out.print(printTaskCompleted);
        } else {
            taskItems[taskItemNo].setIncomplete();
            String printTaskIncomplete = TOP_DIVIDER + UNMARK_TASK_DESC + taskItems[taskItemNo] + BOTTOM_DIVIDER;
            System.out.print(printTaskIncomplete);
        }
    }

    public static void listTaskItems() {
        System.out.print(TOP_DIVIDER + LIST_TASK_ITEM_DESC);

        for (int itemNo = 0; itemNo < taskItemCount; itemNo++) {
            String printItem = WHITESPACE + (itemNo + 1) + DOT + taskItems[itemNo] + System.lineSeparator();
            System.out.print(printItem);
        }

        System.out.print(LIST_TASK_ITEM_BOTTOM_DIVIDER);
    }

    public static void printWelcomeMessage() {
        String printGreet = TOP_DIVIDER + WELCOME_MESSAGE + BOTTOM_DIVIDER;
        System.out.print(printGreet);
    }

    public static void printExitMessage() {
        String printExit = TOP_DIVIDER + EXIT_DESC + BOTTOM_DIVIDER;
        System.out.print(printExit);
    }

    public static void printUnknownCmdErr() {
        String printErrMsg = TOP_DIVIDER + UNKNOWN_CMD_ERR + BOTTOM_DIVIDER;
        System.out.print(printErrMsg);
    }

    public static void printEmptyTodoDescErr() {
        String printErrMsg = TOP_DIVIDER + EMPTY_TODO_DESC_ERR + BOTTOM_DIVIDER;
        System.out.println(printErrMsg);
    }

    public static String getInput(Scanner in) {
        String line = in.nextLine();
        return line;
    }

    public static void processInput(String input) {
        String[] words = input.split(" ", 2);
        String command = words[0];

        switch (command) {
        case COMMAND_LIST_TASK_ITEM:
            listTaskItems();
            break;
        case COMMAND_MARK_TASK:
        case COMMAND_UNMARK_TASK:
            int taskItemNo = Integer.parseInt(words[1]) - 1;
            markTaskItems(taskItemNo, command);
            break;
        case COMMAND_ADD_DEADLINE:
            words = words[1].split(" /by ");
            String deadlineTaskName = words[0];
            String by = words[1];
            addDeadlineTaskItems(deadlineTaskName, by);
            break;
        case COMMAND_ADD_EVENT:
            words = words[1].split(" /from ");
            String eventTaskName = words[0];
            words = words[1].split(" /to ");
            String from = words[0];
            String to = words[1];
            addEventTaskItems(eventTaskName, from, to);
            break;
        case COMMAND_ADD_TODO:
            try {
                String todoTaskName = words[1];
                addTodoTaskItems(todoTaskName);
            } catch (IndexOutOfBoundsException e) {
                printEmptyTodoDescErr();
            }
            break;
        default:
            printUnknownCmdErr();
            break;
        }
    }

    public static void main(String[] args) {
        printWelcomeMessage();
        Scanner in = new Scanner(System.in);

        while (true) {
            String input = getInput(in);

            if (input.equals(COMMAND_EXIT)) {
                printExitMessage();
                break;
            } else {
                processInput(input);
            }
        }
    }
}