package Commands;

import java.util.ArrayList;

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

    public static void printList(ArrayList<Task> taskList) {
        printLine();
        if (taskList.size() == 0) {
            System.out.println("You don't have any tasks added.");
        } else {
            System.out.println("\tHere are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) { // 0-base
                System.out.println("\t" + (i + 1) + "." + taskList.get(i).getDescription());
            }
        }
        printLine();
    }

    public static void printNumberOfTasks(ArrayList<Task> taskList) {
        if (taskList.size() == 1) {
            System.out.println("\tYou have a total of " + taskList.size() + " task in the list");
        } else {
            System.out.println("\tYou have a total of " + taskList.size() + " tasks in the list");
        }
    }

    public static void printTodoMessage(ArrayList<Task> taskList, String todoDescription) {
        printLine();
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t" + todoDescription);
        printNumberOfTasks(taskList);
        printLine();
    }

    public static void printDeadlineMessage(ArrayList<Task> taskList, String deadlineDescription) {
        printLine();
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t" + deadlineDescription);
        printNumberOfTasks(taskList);
        printLine();
    }

    public static void printEventMessage(ArrayList<Task> taskList, String eventDescription) {
        printLine();
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t" + eventDescription);
        printNumberOfTasks(taskList);
        printLine();
    }

    public static void printHelp() {
        printLine();
        System.out.println("Will Implement Soon!");
        printLine();
    }

    public static void printDeleteMessage(ArrayList<Task> taskList, int taskIndex) {
        printLine();
        System.out.println("\tWoosh! This task is now gone: ");
        System.out.println(
                "\t" + taskList.get(taskIndex).getType() + taskList.get(taskIndex).getStatusIcon()
                        + taskList.get(taskIndex).description);
        printLine();
    }

    public static void printFindMessage() {
        printLine();
        System.out.println();
        printLine();
    }
}
