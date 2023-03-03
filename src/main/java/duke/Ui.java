package duke;

import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;

/**
 * <h1>Ui</h1>
 * The Ui class contains the text and methods to display to the user.
 * It contains what Dukebot outputs to the user, including error messages.
 * <p>
 *
 * @author  Tang Yinxuan (Sophie)
 * @version 1.0
 * @since   2023-03-03
 */
public class Ui {
    public static final String OPENING_MSG = "Hello! I'm Dukebot\n\tWhat can I do for you?";
    public static final String CLOSING_MSG = "Goodbye! Hope to see you again soon ^^!";
    public static final String HORIZONTAL = "---------------------------------";
    public static final String MARK_MSG = "Nice! I've marked this task as done:";
    public static final String UNMARK_MSG = "Oki! I've marked this task as not done yet:";
    public static final String UNRECOGNISED_WORD = HORIZONTAL + "\n\tSorry, I don't know what that means!\n" + HORIZONTAL;
    public static final String EMPTY_EVENT = HORIZONTAL + "\n\tCannot add, the description of an event cannot be empty!\n" + HORIZONTAL;
    public static final String EMPTY_DEADLINE = HORIZONTAL + "\n\tCannot add, the description of a deadline cannot be empty!\n" + HORIZONTAL;
    public static final String EMPTY_TODO = HORIZONTAL + "\n\tCannot add, the description of a todo cannot be empty!\n" + HORIZONTAL;
    public static final String EMPTY_LISTNUM = HORIZONTAL + "\n\tI cannot change the status if I don't know the list number!\n" + HORIZONTAL;

    public static final String MATCHING_TASKS = "\n\tHere are the matching tasks in your list: \n";
    public void displayWelcome() {
        System.out.println(HORIZONTAL + "\n\t" + OPENING_MSG + "\n" + HORIZONTAL);
    }

    public void displayUnrecognisedWord() {
        System.out.println(UNRECOGNISED_WORD);
    }

    public void displayGoodBye() {
        System.out.println(HORIZONTAL + "\n\t" + CLOSING_MSG + "\n" + HORIZONTAL);
    }

    public static void displayTaskAddedMessage(Task task) {
        System.out.println(HORIZONTAL + "\n\tGot it! Added this task: " + "\n\t\t" + task.getDescription());
    }
    public static void displayTaskDeletedMessage(Task task) {
        System.out.println(HORIZONTAL + "\n\tNoted! I have deleted this task: " + "\n\t\t" + task.getDescription());
    }

    /**
     * This method displays the current number of tasks in the arraylist of tasks to the user.
     *
     * @param int item - a count of the number of items in the arraylist
     * @return Nothing
     */
    public static void displayNumItemsInList(int item) {
        if (item > 1) {
            System.out.println("\tNow you have " + (item) + " tasks in the list");
        } else {
            System.out.println("\tNow you have " + (item) + " task in the list");
        }
        System.out.println(HORIZONTAL);
    }

    /**
     * This method displays the list of tasks from the arraylist and their respective descriptions to the user.
     *
     * @param ArrayList tasks - the collection that stores all the user's tasks
     * @return Nothing
     */
    public static void displayList(ArrayList tasks) {
        System.out.println(HORIZONTAL + "\n\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\n\t" + (i + 1) + ". " + ((Task) tasks.get(i)).getDescription() + "\n");
        }
        System.out.println(HORIZONTAL);
    }

    public static void displayMatchingTasks() {
        System.out.println(MATCHING_TASKS);
    }

    public static void showEmptyListNumMsg() {
        System.out.println(EMPTY_LISTNUM);
    }

    public static void showEmptyEventMsg() {
        System.out.println(EMPTY_EVENT);
    }
    public static void showEmptyDeadlineMsg() {
        System.out.println(EMPTY_DEADLINE);
    }

    public static void showEmptyTodoMsg() {
        System.out.println(EMPTY_TODO);
    }

}
