package Commands;

import java.util.ArrayList;

import Exceptions.InvalidFindStringException;
import Tasks.Task;

public class PrintCommands {

    /**
     * Prints the welcome message for the user
     */
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

    /**
     * Prints the exit message when user closes the application
     */
    public static void printExitMessage() {
        printLine();
        System.out.print("\tBye. Hope to see you again soon!\n");
        printLine();
    }

    /**
     * Prints the taskList line by line
     * 
     * @param taskList
     */
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

    /**
     * Prints the number of tasks in the task list
     * 
     * @param taskList
     */
    public static void printNumberOfTasks(ArrayList<Task> taskList) {
        if (taskList.size() == 1) {
            System.out.println("\tYou have a total of " + taskList.size() + " task in the list");
        } else {
            System.out.println("\tYou have a total of " + taskList.size() + " tasks in the list");
        }
    }

    /**
     * Prints the relevant message for tasks of type "todo"
     * 
     * @param taskList
     * @param todoDescription
     */
    public static void printTodoMessage(ArrayList<Task> taskList, String todoDescription) {
        printLine();
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t" + todoDescription);
        printNumberOfTasks(taskList);
        printLine();
    }

    /**
     * Prints the relevant message for tasks of type "deadline"
     * 
     * @param taskList
     * @param deadlineDescription
     */
    public static void printDeadlineMessage(ArrayList<Task> taskList, String deadlineDescription) {
        printLine();
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t" + deadlineDescription);
        printNumberOfTasks(taskList);
        printLine();
    }

    /**
     * Prints the relevant message for tasks of type "event"
     * 
     * @param taskList
     * @param eventDescription
     */
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

    /**
     * Prints the relevant message after deleting a task from the task list
     * 
     * @param taskList
     * @param taskIndex
     */
    public static void printDeleteMessage(ArrayList<Task> taskList, int taskIndex) {
        printLine();
        System.out.println("\tWoosh! This task is now gone: ");
        System.out.println(
                "\t" + taskList.get(taskIndex).getType() + taskList.get(taskIndex).getStatusIcon()
                        + taskList.get(taskIndex).description);
    }

    /**
     * Prints the tasks that contain the keyword which the user had specified
     * 
     * @param taskList
     * @param command
     * @throws InvalidFindStringException
     */
    public static void printFindMessage(ArrayList<Task> taskList, String[] command) throws InvalidFindStringException {

        printLine();
        int taskNumber = 1;
        System.out.println("\tHere's what I found :");
        for (Task task : taskList) {
            if (task.getDescription().contains(command[1])) {
                
                switch (task.getType()) {
                    case "[T]":
                        System.out.println("\t" + taskNumber + ". " + task.getDescription());
                        break;

                    case "[D]":
                        System.out.println("\t" + taskNumber + ". " + task.getDescription());
                        break;

                    case "[E]":
                        System.out.println("\t" + taskNumber + ". " + task.getDescription());
                        break;
                }
                taskNumber++;
            } else {
                throw new InvalidFindStringException(
                        "\tUh oh! The task you are looking for does not exist, or there were some issues. Please try again.");
            }
        }
        printLine();
    }
}
