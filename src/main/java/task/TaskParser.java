package task;

import common.Messages;
import storage.Storage;
import ui.TextUi;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A Class to parse and execute the different types of tasks the Duke program will handle.
 * Has operations that will create, delete, mark, unmark, list, and find tasks.
 */
public class TaskParser {
    private static ArrayList<Task> listOfTasks = new ArrayList<>();

    /**
     * A constructor to take in an ArrayList of tasks.
     *
     * @param listOfTasks An ArrayList of user defined tasks.
     */
    public TaskParser(ArrayList<Task> listOfTasks) {
        TaskParser.listOfTasks = listOfTasks;
    }

    /**
     * Creates a Todo Task.
     *
     * @param description Description of the task.
     * @return A Todo object for task.
     */
    public Todo createTodoTask(String description) {
        return new Todo(description);
    }

    /**
     * Creates a Deadline Task.
     *
     * @param description Description of the task.
     * @param by          The deadline of the task.
     * @return A Deadline object for the task.
     */
    public Deadline createDeadlineTask(String description, String by) {
        return new Deadline(description, by);
    }

    /**
     * Creates a Event Task.
     *
     * @param description Description of the task.
     * @param time        The from and to time of the task.
     * @return A Event object for the task.
     */
    public Event createEventTask(String description, String time) {
        return new Event(description, time);
    }

    /**
     * Find a task based on the keyword specified.
     *
     * @param ui      The Ui instance. Use to display messages to users.
     * @param keyword The keyword specified by the user.
     */
    public void findTask(TextUi ui, String keyword) {
        if (listOfTasks.isEmpty()) {
            ui.printMessage(Messages.MESSAGE_COMMAND_FIND_EMPTY_LIST);
        } else {
            ui.printMessage(Messages.MESSAGE_COMMAND_FIND_MATCH);
            Task task;
            for (int i = 0; i < listOfTasks.size(); i++) {
                task = listOfTasks.get(i);
                if (task.toString().contains(keyword)) {
                    System.out.println(i + 1 + "." + task);
                }

            }
        }
    }

    /**
     * Mark the specified task as done.
     *
     * @param ui               The Ui instance. Use to display messages to users.
     * @param storage          The storage instance. Use to write data into the text file.
     * @param taskNumberInList The task number to be marked as done.
     */
    public void markTask(TextUi ui, Storage storage, int taskNumberInList) {
        Task task = listOfTasks.get(taskNumberInList);
        task.markAsDone();
        try {
            storage.writeToFile(listOfTasks);
        } catch (IOException e) {
            ui.printMessage(String.format(Messages.GENERIC_ERROR, e));
        }
        ui.printMessage(Messages.MESSAGE_COMMAND_MARK);
        System.out.println(task);
    }

    /**
     * Unmark the specified task as undone.
     *
     * @param ui               The Ui instance. Use to display messages to users.
     * @param storage          The storage instance. Use to write data into the text file.
     * @param taskNumberInList The task number to be unmarked.
     */
    public void unmarkTask(TextUi ui, Storage storage, int taskNumberInList) {
        Task task = listOfTasks.get(taskNumberInList);
        task.markAsUndone();
        try {
            storage.writeToFile(listOfTasks);
        } catch (IOException e) {
            ui.printMessage(String.format(Messages.GENERIC_ERROR, e));
        }
        ui.printMessage(Messages.MESSAGE_COMMAND_UNMARK);
        System.out.println(task);
    }

    /**
     * Delete the specified task and print the list of user tasks.
     *
     * @param ui               The Ui instance. Use to display messages to users.
     * @param storage          The storage instance. Use to write data into the text file.
     * @param taskNumberInList The task number to be removed from the list.
     */
    public void deleteAndPrintTask(TextUi ui, Storage storage, int taskNumberInList) {
        Task task = listOfTasks.get(taskNumberInList);
        listOfTasks.remove(taskNumberInList);
        try {
            storage.writeToFile(listOfTasks);
        } catch (IOException e) {
            ui.printMessage(String.format(Messages.GENERIC_ERROR, e));
        }
        ui.printMessage(Messages.MESSAGE_COMMAND_DELETE);
        System.out.println(task);
        ui.printMessage(String.format(Messages.MESSAGE_COMMAND_LIST_SIZE, listOfTasks.size()));
    }

    /**
     * Add the user specified task and print out the list of user tasks.
     *
     * @param task    The task object to be added. (Todo, Deadline, Event)
     * @param ui      The Ui instance. Use to display messages to users.
     * @param storage The storage instance. Use to write data into the text file.
     */
    public void addAndPrintTask(Task task, TextUi ui, Storage storage) {
        listOfTasks.add(task);
        ui.printMessage(Messages.MESSAGE_COMMAND_ADD_TASK);
        System.out.println(task);
        ui.printMessage(String.format(Messages.MESSAGE_COMMAND_LIST_SIZE, listOfTasks.size()));
        try {
            storage.writeToFile(listOfTasks);
        } catch (IOException e) {
            ui.printMessage(String.format(Messages.GENERIC_ERROR, e));
        }
    }

    /**
     * Print out the tasks in the list.
     *
     * @param ui The Ui instance. Use to display messages to users.
     */
    public void listTasks(TextUi ui) {
        if (listOfTasks.isEmpty()) {
            ui.printMessage(Messages.MESSAGE_COMMAND_LIST_EMPTY);
        } else {
            ui.printMessage(Messages.MESSAGE_COMMAND_LIST_TASKS);
            Task task;
            for (int i = 0; i < listOfTasks.size(); i++) {
                task = listOfTasks.get(i);
                System.out.println(i + 1 + "." + task);
            }
        }
    }

    /**
     * Check if the task number is a valid number in the list.
     *
     * @param taskNumberInList The task number given.
     * @return true if it is a number that exist in the list. false otherwise.
     */
    public static boolean isValidTaskNumber(int taskNumberInList) {
        if (taskNumberInList >= 0 && taskNumberInList < listOfTasks.size()) {
            return true;
        }
        return false;
    }
}
