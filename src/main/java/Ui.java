import task.Task;

/**
 * Represents messages that Duke is able to print out to display to the user.
 */
public class Ui {
    public static final String LOGO = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";
    /**
     * Prints the welcome message.
     */
    public void welcomeMessage() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints the farewell message.
     */
    public void farewellMessage() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints error messages.
     *
     * @param errorMessage the String containing the error message.
     */
    public void printErrorMessage(String errorMessage) {
        System.out.println("    ____________________________________________________________");
        System.out.println(errorMessage);
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints the given task list.
     *
     * @param taskList the task list that is to be printed.
     */
    public void printTaskList(TaskList taskList) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            System.out.println("     " + (i + 1) + "." + taskList.getTask(i));
        }
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints the task that is marked.
     *
     * @param markedTask the task that has been marked.
     */
    public void printMarkedTask(Task markedTask) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + markedTask);
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints the task that is unmarked.
     *
     * @param unmarkedTask the task that has been unmarked.
     */
    public void printUnmarkedTask(Task unmarkedTask) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + unmarkedTask);
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints the added task.
     *
     * @param addedTask the task that is added.
     * @param numOfTasks the total number of tasks in the task list.
     */
    public void printAddedTask(Task addedTask, int numOfTasks) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + addedTask);
        System.out.println("     Now you have " + numOfTasks + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints the deleted task.
     *
     * @param deletedTask the task that is deleted.
     * @param numOfTasks the total number of tasks in the task list.
     */
    public void printDeletedTask(Task deletedTask, int numOfTasks) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + deletedTask);
        System.out.println("     Now you have " + numOfTasks + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints the matching found tasks.
     *
     * @param taskList the task list that will be used to find the tasks.
     */
    public void printFoundTask(TaskList taskList) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the matching tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            System.out.println("     " + (i + 1) + "." + taskList.getTask(i));
        }
        System.out.println("    ____________________________________________________________");
    }

}
