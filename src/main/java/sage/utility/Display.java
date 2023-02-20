package sage.utility;

import java.sql.SQLOutput;
import java.util.ArrayList;

import sage.tasktypes.Deadline;
import sage.tasktypes.Event;
import sage.tasktypes.Todo;
import sage.tasktypes.Task;

/**
 * A class containing methods for printing text on the console.
 */

public class Display {
    private static final String LINE =
            "█████╗█████╗█████╗█████╗█████╗█████╗█████╗\n" +
                    "╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝";
    private static final String LOGO = "░██████╗░█████╗░░██████╗░███████╗\n" +
            "██╔════╝██╔══██╗██╔════╝░██╔════╝\n" +
            "╚█████╗░███████║██║░░██╗░█████╗░░\n" +
            "░╚═══██╗██╔══██║██║░░╚██╗██╔══╝░░\n" +
            "██████╔╝██║░░██║╚██████╔╝███████╗\n" +
            "╚═════╝░╚═╝░░╚═╝░╚═════╝░╚══════╝";

    public String printLine() {
        return LINE;
    }

    public void printWelcomeUser() {
        String welcome = "Hello! I'm SAGE, the knowledgeable one\n"
                + "What can I do for you today?\n";
        System.out.println(LOGO);
        System.out.println(welcome);
    }

    public void printGoodByeUser() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printResumeSession() {
        System.out.println("Info: Data from previous session is resumed");
    }

    /**
     * This method prints a message to the console indicating the addition of a TODO task to the task list and its details.
     *
     * @param taskObj   A task object (TODO/DEADLINE/EVENT)
     * @param taskCount The number of tasks in list.
     */
    public void printAddedTask(Task taskObj, int taskCount) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(taskObj);
        System.out.println("Now you have " + taskCount + " tasks in the list");
        System.out.println(LINE);
    }

    public void printUnknownInput() {
        System.out.println("I don't understand what you mean, please try again!");
    }


    /**
     * This method is used to display a marked/unmark task as not done/done in the task list.
     *
     * @param list       the list of tasks
     * @param taskNumber the taskNumber index that is to be unmarked
     * @param isToMark   whether operation executed is to mark a task
     */
    public void printMarking(ArrayList<Task> list, int taskNumber, boolean isToMark) {
        Task taskObj = list.get(taskNumber - 1);
        if (isToMark) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(taskObj);
    }

    /**
     * This method is used to display delete operation in the task list.
     *
     * @param list       the list of tasks
     * @param taskNumber the taskNumber index of the task that is to be deleted
     */
    public void printDeletedTask(ArrayList<Task> list, int taskNumber) {
        Task taskObj = list.get(taskNumber - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskObj);
        System.out.println("Now you have " + String.valueOf(list.size() - 1) + " tasks in the list.");
    }

    public void printSearchTask(ArrayList<Task> results) {
        System.out.println(LINE);
        if (results.isEmpty()) {
            System.out.println("Found no matching query. Please try another again!");
        } else {
            System.out.println("Found " + results.size() + " matching queries!");
            for (int i = 0; i < results.size(); i++) {
                System.out.print(i + 1 + ".");
                System.out.println(results.get(i));
            }

        }
        System.out.println(LINE);
    }

}
