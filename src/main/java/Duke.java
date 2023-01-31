import java.util.Scanner;

public class Duke {
    private static final int MAX_ITEMS = 100;
    private static final String TOP_DIVIDER = "════════════════════════════════════════════════════════════\n";
    public static final String BOTTOM_DIVIDER = "\n════════════════════════════════════════════════════════════\n\n";
    public static final String ADD_TASK_ITEM_DESC = " added: ";
    public static final String LIST_TASK_ITEM_DESC = " Here are the tasks in your list:\n";
    public static final String COMMAND_MARK_TASK = "mark";
    public static final String COMMAND_UNMARK_TASK = "unmark";
    public static final String MARK_TASK_DESC = " Good Job! I've marked this task as completed:\n";
    public static final String UNMARK_TASK_DESC = " Noted, I have marked this task as incomplete:\n";
    public static final String OPEN_BRACKET = " [";
    public static final String CLOSE_BRACKET = "] ";
    public static final String WHITESPACE = " ";
    public static final String DOT = ".";
    public static final String LIST_TASK_ITEM_BOTTOM_DIVIDER
            = "════════════════════════════════════════════════════════════\n\n";
    public static final String WELCOME_MESSAGE = " Hello! I'm Chatty\n How can I help you?";
    public static final String COMMAND_LIST_TASK_ITEM = "list";
    public static final String COMMAND_EXIT = "bye";
    public static final String EXIT_DESC = " Goodbye. Hope to see you again soon!\n";

    private static Task[] taskItems = new Task[MAX_ITEMS];
    private static int taskItemCount = 0;

    public static void addTaskItems(String taskName) {
        taskItems[taskItemCount] = new Task(taskName);
        taskItemCount++;
        String printAddItem = TOP_DIVIDER + ADD_TASK_ITEM_DESC + taskName + BOTTOM_DIVIDER;
        System.out.print(printAddItem);
    }

    public static void markTaskItems(int taskItemNo, String command) {
        if (command.startsWith(COMMAND_MARK_TASK)) {
            taskItems[taskItemNo].setCompleted();
            String printTaskCompleted = TOP_DIVIDER + MARK_TASK_DESC + OPEN_BRACKET
                    + taskItems[taskItemNo].getTaskStatus() + CLOSE_BRACKET
                    + taskItems[taskItemNo].getTaskName() + BOTTOM_DIVIDER;
            System.out.print(printTaskCompleted);
        } else {
            taskItems[taskItemNo].setIncomplete();
            String printTaskIncomplete = TOP_DIVIDER + UNMARK_TASK_DESC + OPEN_BRACKET
                    + taskItems[taskItemNo].getTaskStatus() + CLOSE_BRACKET
                    + taskItems[taskItemNo].getTaskName() + BOTTOM_DIVIDER;
            System.out.print(printTaskIncomplete);
        }
    }

    public static void listTaskItems() {
        System.out.print(TOP_DIVIDER + LIST_TASK_ITEM_DESC);

        for (int itemNo = 0; itemNo < taskItemCount; itemNo++) {
            String printItem = WHITESPACE + (itemNo + 1) + DOT + OPEN_BRACKET
                    + taskItems[itemNo].getTaskStatus() + CLOSE_BRACKET
                    + taskItems[itemNo].getTaskName() + System.lineSeparator();
            System.out.print(printItem);
        }

        System.out.print(LIST_TASK_ITEM_BOTTOM_DIVIDER);
    }

    public static void printWelcomeMessage() {
        String greet = TOP_DIVIDER + WELCOME_MESSAGE + BOTTOM_DIVIDER;
        System.out.print(greet);
    }

    public static String getInput() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        return line;
    }

    public static boolean processInput(String input) {
        if (input.equals(COMMAND_LIST_TASK_ITEM)) {
            listTaskItems();
        } else if (input.startsWith(COMMAND_MARK_TASK) || input.startsWith(COMMAND_UNMARK_TASK)) {
            int taskItemNo = Integer.parseInt(input.substring(input.length() - 1)) - 1;
            markTaskItems(taskItemNo, input);
        } else if (input.equals(COMMAND_EXIT)) {
            String printExit = TOP_DIVIDER + EXIT_DESC + TOP_DIVIDER;
            System.out.print(printExit);
            return true;
        } else {
            addTaskItems(input);
        }
        return false;
    }

    public static void main(String[] args) {
        printWelcomeMessage();

        while (true) {
            String input = getInput();

            if (processInput(input)) {
                break;
            }
        }
    }
}