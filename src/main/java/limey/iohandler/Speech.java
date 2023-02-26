package limey.iohandler;

import limey.command.Task;
import limey.exception.taskListEmpty;

import java.util.ArrayList;

public class Speech {
    public static void sayHi() {
        System.out.println("\tHello! I am limey, What can I do for you?");
    }

    public static void sayBye() {
        System.out.println("\tBye! Hope to see you again soon. :)");
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void printErrorLine() {
        System.out.println("/////////////////////////////////////////////////////////////");
    }

    public static void printAdded(Task task, int numTasks) {
        printLine();
        System.out.println("\t" + "added: " + task.getTaskName());
        System.out.println("\tTotal number of current tasks: " + numTasks);
        printLine();
    }

    public static void printMarked(Task task) {
        printLine();
        System.out.println("\t" + "I have just marked this task as done:");
        System.out.println("\t" + task.getTaskIdentity());
        printLine();
    }

    public static void invalidMessage(String errorMessage) {
        printErrorLine();
        System.out.println("\tSorry that was an invalid command. Error: " + errorMessage);
        printErrorLine();
    }

    public static void printUnmarked(Task task) {
        printLine();
        System.out.println("\t" + "This task has been marked as not done yet:");
        System.out.println("\t" + task.getTaskIdentity());
        printLine();
    }

    public static void printTaskList(ArrayList<Task> taskList, int numTasks) {
        printLine();
        if (taskList.isEmpty()) {
            System.out.println("You currently have 0 tasks.");
        } else {
            for (int listNum = 1; listNum <= numTasks; listNum++) {
                System.out.println("\t" + listNum + "." + taskList.get(listNum - 1).getTaskIdentity());
            }
        }
        printLine();
    }

    public static void printDeleteTask(Task task) {
        printLine();
        System.out.println("\t" + "This task has been deleted:");
        System.out.println("\t" + task.getTaskIdentity());
        printLine();
    }
    public static void printNoFoundTask(String searchTerm) {
        printLine();
        System.out.println("\t" + "No task name found containing "+ searchTerm);
        printLine();
    }
}
