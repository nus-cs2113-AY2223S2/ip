/**
 * A tool to print messages to the terminal. Used by Duke chatbot.
 */
public class DukeNUSPrinter {
    public static final String LOGO = ""
        + "     ____        _          __    _ _   _ ______\n"
        + "    |  _ \\ _   _| | _____  |   \\ | | | | | _____|\n"
        + "    | | | | | | | |/ / _ \\ | |\\ \\| | | | |_____ |\n"
        + "    | |_| | |_| |   <  __/ | | \\ \\ | |_| |____| | \n"
        + "    |____/ \\__,_|_|\\_\\___| |_|  \\__|_____|______|\n";
    public static final String INDENT = "    ";
    public static void printHorizontalLine() {
        System.out.println("    ____________________________________________________________");
    }

    public static void printMessage(String message) {
        printHorizontalLine();
        System.out.println(INDENT + message);
        printHorizontalLine();
    }

    public static void printAddedTask(String taskDisplayDescription, int taskCount) {
        printHorizontalLine();
        System.out.println(INDENT + "Got it. I've added this task:");
        System.out.println(INDENT + taskDisplayDescription);
        System.out.println(INDENT + "You now have " + taskCount + " tasks in the list.");
        printHorizontalLine();
    }
    public static void printTasks(Task[] tasks, int taskCount) {
        printHorizontalLine();
        System.out.println(INDENT + "Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i += 1) {
            System.out.println(INDENT + (i + 1) + '.' + tasks[i].getTaskString());
        }
        printHorizontalLine();
    }

    public static void printWelcomeMessage() {
        printMessage("Hello from\n" + LOGO + INDENT + "Hello! I'm Duke-NUS Medical School. \n" + INDENT + "What can I do for you?");
    }

    public static void printFarewellMessage() {
        printMessage("Bye. Hope to see you again soon!");
    }
}
