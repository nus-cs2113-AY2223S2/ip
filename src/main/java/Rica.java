import java.util.ArrayList;
import java.util.Scanner;

public class Rica {
    private static final String LINE = "____________________________________________________________";
    private static final String LIST_TRIGGER = "list";
    private static final String MARK_TRIGGER = "mark";
    private static final String TODO_TRIGGER = "todo";
    private static final String UNMARK_TRIGGER = "unmark";
    private static final String BYE_TRIGGER = "bye";
    private static final String BYE_PHRASE = " Leaving so soon? Come back anytime, I'll be happy to help!";
    private static TaskManager taskManager;

    private static TaskManager getTaskManager() {
        return Rica.taskManager;
    }

    public static void main(String[] args) {
        printlnWithIndent(Rica.LINE);
        printlnWithIndent(" Hello! I'm R.I.C.A.");
        printlnWithIndent((" That's Really-Intelligent-Chat-Assistant for you!"));
        printlnWithIndent(" How may I be of assistance?");
        printlnWithIndent(Rica.LINE);
        taskManager = new TaskManager();
        Rica.runCommands();
    }

    private static void printlnWithIndent(String line) {
        System.out.print("    ");
        System.out.println(line);
    }

    /**
     * Parse the command entered into CLI and execute the corresponding actions
     */
    private static void runCommands() {
        Scanner in = new Scanner(System.in);
        String command = "";
        do {
            command = in.nextLine();
            printlnWithIndent(Rica.LINE);
            String[] params = command.split(" ");
            switch (params[0]) {
            case Rica.MARK_TRIGGER:
                int indexOfTodo = Integer.valueOf(params[1]) - 1;
                Rica.getTaskManager().markDone(indexOfTodo);
                break;
            case Rica.UNMARK_TRIGGER:
                indexOfTodo = Integer.valueOf(params[1]) - 1;
                Rica.getTaskManager().unmarkDone(indexOfTodo);
                break;
            case Rica.LIST_TRIGGER:
                Rica.getTaskManager().printTasks();
                break;
            case Rica.TODO_TRIGGER:
                Rica.getTaskManager().createTask(command);
                break;
            case Rica.BYE_TRIGGER:
                printlnWithIndent(Rica.BYE_PHRASE);
                break;
            }
            printlnWithIndent(Rica.LINE);
        } while (!command.equals(Rica.BYE_TRIGGER));
    }
}
