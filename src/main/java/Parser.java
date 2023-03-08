import exceptions.DukeException;

import java.util.ArrayList;

import static messages.ErrorMessages.MESSAGE_INVALID_INDEX_ERROR;
import static messages.operationalMessages.MESSAGE_UNMARK_TASK;
import static messages.operationalMessages.MESSAGE_MARK_TASK;
import static messages.operationalMessages.MESSAGE_REMOVE_TASK;
import static messages.operationalMessages.MESSAGE_NO_MATCHING_TASK;
import static messages.operationalMessages.MESSAGE_MATCHING_TASK;

public class Parser {

    /**
     * prints out all the items in the list of tasks
     *
     * @param items ArrayList of Tasks containing all the task items added
     */
    public static void displayList(ArrayList<Task> items) {
        System.out.println("...\n" + "Here are the tasks in your list:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + "." + items.get(i));
        }
    }

    /**
     * marks a task as done
     *
     * @param items     ArrayList of Tasks containing all the task items added
     * @param arguments String containing a number that is one more than the index of the task to be marked as done
     */
    public static void markTask(ArrayList<Task> items, String arguments) {
        try {
            int index = Integer.parseInt(arguments) - 1;
            items.get(index).isDone = true;
            System.out.println(MESSAGE_MARK_TASK + items.get(index));
        } catch (NumberFormatException e) {
            System.out.println(MESSAGE_INVALID_INDEX_ERROR);
        }
    }

    /**
     * unmarks a task as done
     *
     * @param items     ArrayList of Tasks containing all the task items added
     * @param arguments String containing a number that is one more than the index of the task to be unmarked
     */
    public static void unmarkTask(ArrayList<Task> items, String arguments) {
        try {
            int index = Integer.parseInt(arguments) - 1;
            items.get(index).isDone = false;
            System.out.println(MESSAGE_UNMARK_TASK + items.get(index));
        } catch (NumberFormatException e) {
            System.out.println(MESSAGE_INVALID_INDEX_ERROR);
        }
    }

    /**
     * removes a task from items
     *
     * @param items     ArrayList of Tasks containing all the task items added
     * @param arguments String containing a number that is one more than the index of the task to be removed
     */
    public static void removeTask(ArrayList<Task> items, String arguments) {
        try {
            int index = Integer.parseInt(arguments) - 1;
            items.remove(index);
            System.out.println(MESSAGE_REMOVE_TASK + (index + 1));
        } catch (NumberFormatException e) {
            System.out.println(MESSAGE_INVALID_INDEX_ERROR);
        }
    }

    /**
     * adds a new Todo task to items
     *
     * @param items     ArrayList of Tasks containing all the task items added
     * @param arguments String containing description of Todo to be added
     */
    public static void addNewTodo(ArrayList<Task> items, String arguments) {
        Task newTask = TaskList.createNewTodo(arguments);
        items.add(newTask);
    }

    /**
     * adds a new Event task to items
     *
     * @param items     ArrayList of Tasks containing all the task items added
     * @param arguments String containing description of Event to be added
     */
    public static void addNewEvent(ArrayList<Task> items, String arguments) {
        try {
            Task newEvent = TaskList.createNewEvent(arguments);
            items.add(newEvent);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * adds a new Deadline task to items
     *
     * @param items     ArrayList of Tasks containing all the task items added
     * @param arguments String containing description of Deadline to be added
     */
    public static void addNewDeadline(ArrayList<Task> items, String arguments) {
        try {
            Task newDeadline = TaskList.createNewDeadline(arguments);
            items.add(newDeadline);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * finds matching tasks from the task list
     *
     * @param items    ArrayList of Tasks containing all the task items added
     * @param keywords Array of Strings of keywords to be checked for matches
     * @return ArrayList of indexes of matching tasks
     */
    public static ArrayList<Integer> findMatchingTasks(ArrayList<Task> items, String[] keywords) {
        ArrayList<Integer> matchingTaskIndex = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            for (String keyword : keywords) {
                if (items.get(i).description.contains(keyword)) {
                    matchingTaskIndex.add(i);
                }
            }
        }
        return matchingTaskIndex;
    }

    /**
     * prints matching tasks from the task list
     *
     * @param items     ArrayList of Tasks containing all the task items added
     * @param arguments String of keywords to be checked for matches
     */
    public static void printMatchingTasks(ArrayList<Task> items, String arguments) {
        boolean isMessagePrinted = false;
        boolean isKeywordFound = false;
        String[] keywords = arguments.split(" ");
        ArrayList<Integer> matchingTasks = findMatchingTasks(items, keywords);
        for (int matchingTaskIndex : matchingTasks) {
            isKeywordFound = true;
            if (!isMessagePrinted) {
                System.out.println(MESSAGE_MATCHING_TASK);
                isMessagePrinted = true;
            }
            System.out.println((matchingTaskIndex + 1) + "." + items.get(matchingTaskIndex));
        }
        if (!isKeywordFound) {
            System.out.println(MESSAGE_NO_MATCHING_TASK);
        }
    }
}