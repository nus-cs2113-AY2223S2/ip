package duke.print;

import duke.task.Task;

import java.util.ArrayList;

/**
 *
 * Custom Print class to include methods
 * that will be used throughout the Duke project
 *
 */
public class Print {

    /**
     * Simplifies the default Java System.out.print method
     *
     * @param str Input string to print
     */
    public static void print(String str) {
        System.out.print(str);
    }

    /**
     * Simplifies the default Java System.out.println method
     *
     * @param str Input string to print with a newline character
     *            at the end
     */
    public static void println(String str) {
        System.out.println(str);
    }

    /**
     * Prints out one line of underscores, with a newline character at the end
     * <p>
     * 4 spaces is included at the front of the string
     */
    public static void printOneLine() {
        System.out.println("    ____________________________________");
    }

    /**
     * Prints the type of task and the marked-done status of the task
     *
     * @param selectedTask The selected task to print its task Type and Done status
     */
    public static void printTypeAndStatus(Task selectedTask) {
        print("       [" + selectedTask.getTypeIcon() + "]" + "[" + selectedTask.getDoneIcon() + "] ");
    }

    /**
     * Prints welcome message at the start of the program
     * Prints list of commands that DUKE can currently understand
     */
    public static void showWelcomeMessage() {
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";
        println(logo);

        printOneLine();
        println("    Hello! I'm Duke");
        println("    What can I do for you?");

        println("");
        println("    Here are some commands I can understand: ");
        println("");

        println("    todo [task description]                          --> to add a todo task");
        println("    deadline [task description] /by [due date]       --> to add a deadline");
        println("    event [task description] /from [when] /to [when] --> to add an event");
        println("    mark [task number]                               --> to mark a task as done");
        println("    unmark [task number]                             --> to unmark a task (i.e. not done)");
        println("    list                                             --> to show the lists of tasks");
        println("    bye                                              --> to exit the program");

        println("");
        printOneLine();
    }

    /**
     * Prints exit message at the end of the program
     */
    public static void showExitMessage() {
        printOneLine();
        println("    Bye. Hope to see you again soon!");
        printOneLine();
    }


    /**
     * Prints the list of tasks
     *
     * @param taskList the list of tasks to print out
     */
    public static void printTaskList(ArrayList<Task> taskList) {
        printOneLine();
        if (taskList.isEmpty()) {
            println("    Oops, I guess you have no tasks right now.");
            printOneLine();
        } else {
            println("     Here are the tasks in your list:");

            for (int i = 0; i < taskList.size(); i += 1) {
                if (taskList.get(i) == null) {
                    break;
                }

                print("     " + (i + 1) + ".");
                print("[" + taskList.get(i).getTypeIcon() + "]");
                print("[" + taskList.get(i).getDoneIcon() + "] " + taskList.get(i).getDescription());

                switch (taskList.get(i).getTypeIcon()) {
                    case "D":
                        println(" (by:" + taskList.get(i).getBy() + ")");
                        break;

                    case "E":
                        println("(from: " + taskList.get(i).getFrom() + " to:" + taskList.get(i).getTo() + ")");
                        break;

                    case "T":
                        println("");
                }
            }
            printOneLine();
        }
    }


    /**
     * Prints the details of a newly added task
     *
     * @param taskToAdd The newly added task to print the details of.
     * @param taskList  The list of tasks where the newly added task is found
     */
    public static void printAddingOneTask(Task taskToAdd, ArrayList<Task> taskList) {
        printOneLine();
        println("     Got it. I've added this task:");
        printTypeAndStatus(taskToAdd);

        if (taskToAdd.isTodo()) {
            print(taskToAdd.getDescription());

        } else if (taskToAdd.isDeadline()) {

            print(taskToAdd.getDescription());
            print(" (by:" + taskToAdd.getBy() + ")");

        } else if (taskToAdd.isEvent()) {

            print(taskToAdd.getDescription());
            print("(from: " + taskToAdd.getFrom() + " to:" + taskToAdd.getTo() + ")");

        }
        println("");

        println("     Now you have " + taskList.size() + " tasks in the list");

        printOneLine();
    }

    /**
     * Prints a task when it has been marked on unmarked as DONE.
     * @param selectedTask The task that has been marked or unmarked.
     */
    public static void printMarkingOrUnmarkingOneTask(Task selectedTask) {
        printTypeAndStatus(selectedTask);
        print(selectedTask.getDescription());

        switch (selectedTask.getTypeIcon()) {
            case "D":
                println(" (by:" + selectedTask.getBy() + ")");
                break;

            case "E":
                println("(from: " + selectedTask.getFrom() + " to:" + selectedTask.getTo() + ")");
                break;

            default:
                println("");
        }

        printOneLine();
    }

    /**
     * Prints the details of a deleted task
     *
     * @param taskList     The list of tasks, to print the size (i.e. how many tasks in the list)
     * @param taskToDelete The task to print the details of.
     */
    public static void printDeletingOneTask(ArrayList<Task> taskList, Task taskToDelete) {
        printOneLine();
        println("     Noted. I've removed this task:");
        printTypeAndStatus(taskToDelete);

        if (taskToDelete.isTodo()) {
            print(taskToDelete.getDescription());

        } else if (taskToDelete.isDeadline()) {

            print(taskToDelete.getDescription());
            print(" (by:" + taskToDelete.getBy() + ")");

        } else if (taskToDelete.isEvent()) {

            print(taskToDelete.getDescription());
            print("(from: " + taskToDelete.getFrom() + " to:" + taskToDelete.getTo() + ")");

        }
        println("");

        if (taskList.isEmpty()) {
            println("     Well done! You have no more tasks!");
        } else {
            println("     Now you have " + taskList.size() + " tasks in the list");
        }

        printOneLine();
    }
}
