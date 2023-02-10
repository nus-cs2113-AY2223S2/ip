package duke.print;

import duke.task.Task;

/**
 *
 * Custom Print class to include methods
 * that will be used throughout the Duke project
 *
 */
public class Print {
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
    public static void printTaskList(Task[] taskList) {
        printOneLine();
        println("     Here are the tasks in your list:");

        for (int i = 0; i < taskList.length; i += 1) {
            if (taskList[i] == null) {
                break;
            }

            print("     " + (i + 1) + ".");
            print("[" + taskList[i].getTypeIcon() + "]");
            print("[" + taskList[i].getDoneIcon() + "] " + taskList[i].getDescription());

            switch (taskList[i].getTypeIcon()) {
                case "D":
                    println(" (by:" + taskList[i].getBy() + ")");
                    break;

                case "E":
                    println("(from: " + taskList[i].getFrom() + " to:" + taskList[i].getTo() + ")");
                    break;

                case "T":
                    println("");
            }
        }
        printOneLine();
    }

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
     * Prints the details of a newly added task
     *
     * @param task      The task to print the details of.
     * @param taskIndex The index of the current last task, to determine the current number of tasks in the list
     */
    public static void printAddingOneTask(Task task, int taskIndex) {
        printOneLine();
        println("     Got it. I've added this task:");
        print("       [" + task.getTypeIcon() + "]" + "[" + task.getDoneIcon() + "] ");

        if (task.isTodo()) {
            print(task.getDescription());

        } else if (task.isDeadline()) {

            print(task.getDescription());
            print(" (by:" + task.getBy() + ")");

        } else if (task.isEvent()) {

            print(task.getDescription());
            print("(from: " + task.getFrom() + " to:" + task.getTo() + ")");

        }
        println("");

        println("     Now you have " + (taskIndex + 1) + " tasks in the list");

        printOneLine();
    }
}
