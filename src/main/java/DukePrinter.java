/**
 * A tool to print messages to the terminal. Used by Duke chatbot.
 */
public class DukePrinter {
    public static final String LOGO = ""
        + "     ____        _        \n"
        + "    |  _ \\ _   _| | _____ \n"
        + "    | | | | | | | |/ / _ \\\n"
        + "    | |_| | |_| |   <  __/\n"
        + "    |____/ \\__,_|_|\\_\\___|\n";

    public static void printHorizontalLine() {
        System.out.println("    ____________________________________________________________");
    }
    public static void printMessage(String message) {
        printHorizontalLine();
        System.out.println("    " + message);
        printHorizontalLine();
    }
    public static void printTasks(Task[] tasks, int taskCount) {
        printHorizontalLine();
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i += 1) {
            System.out.println("    " + (i + 1) + '.' + tasks[i].getTaskString());
        }
        printHorizontalLine();
    }
    public static void printWelcomeMessage() {
        printMessage("Hello from\n" + LOGO + "    Hello! I'm Duke\n" + "    What can I do for you?");
    }
    public static void printFarewellMessage() {
        printMessage("Bye. Hope to see you again soon!");
    }
}
