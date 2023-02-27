import Tasks.Task;

public class PrintCommands {

    public static void printWelcomeMessage() {
        String logo = "\t ____   _              __    __ \n"
                + "\t/  __\\ | |     _____  |   \\/   |\n"
                + "\t| /    | |    /  _  \\ | |\\  /| |\n"
                + "\t| \\ __ | |___ | |_| | | | \\/ | |\n"
                + "\t\\____/ |____/ \\_____/ |_|    |_|\n";

        System.out.println("\tHello from\n" + logo);

        System.out.println("\tHello! I'm CLoM!\n");
        System.out.print("\tWhat can I do for you?\n");
        PrintCommands.printLine();
    }

    protected static void printLine() {
        System.out.println("\t==========================================");
    }

    public static void printExitMessage() {
        printLine();
        System.out.print("\tBye. Hope to see you again soon!\n");
        printLine();
    }

    public static void printList(Task t[], int counter) {
        printLine();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < counter; i++) { // 0-base
            System.out.println("\t" + (i + 1) + "." + t[i].getDescription());
        }
        printLine();
    }

    public static void printNumberOfTasks(int counter) {
        if (counter == 1) {
            System.out.println("\tYou have a total of " + counter + " task in the list");
        } else {
            System.out.println("\tYou have a total of " + counter + " tasks in the list");
        }
    }

    public static void printTodoMessage(Task t, String tododescription, int counter) {
        printLine();
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t" + t.getDescription());
        printNumberOfTasks(counter);
        printLine();
    }

    public static void printDeadlineMessage(Task t, String deadlineDescription, int counter) {
        printLine();
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t" + t.getDescription());
        printNumberOfTasks(counter);
        printLine();
    }

    public static void printEventMessage(Task t, String eventDescription, int counter) {
        printLine();
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t" + t.getDescription());
        printNumberOfTasks(counter);
        printLine();
    }

    public static void printHelp() {
        printLine();
            System.out.println("Will Implement Soon!");
        printLine();
    }
}
