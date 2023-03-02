package duke.tasks;

import duke.DukeException;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Class organizes all tasks inputted into a list.
 */
public class TaskList {

    private ArrayList<Task> tasks;
    private int numTasks;

    /**
     * Creates a TaskList object and initializes tasks and numTaks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
        // setup filler task
        Task fillerTask = new Task("filler", false);
        tasks.add(fillerTask);
    }

    /**
     * Prints all tasks currently in tasks list.
     */
    public void listTasks() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 1; i <= numTasks; i++) {
            Task currTask = tasks.get(i);
            System.out.println(i + ". " + currTask.toString());
        }
    }

    /**
     * Marks a task as completed.
     *
     * @param taskNumToMark The number of the task in the list to mark.
     */
    public void markTask(int taskNumToMark) {
        Task taskToMark = tasks.get(taskNumToMark);
        taskToMark.mark();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(taskToMark.toString());
    }

    /**
     * Unmarks a task as not completed.
     *
     * @param taskNumToUnmark The number of the task in the list to unmark.
     */
    public void unmarkTask(int taskNumToUnmark) {
        Task taskToUnmark = tasks.get(taskNumToUnmark);
        taskToUnmark.unmark();
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println(taskToUnmark.toString());
    }

    /**
     * Adds a deadline task to tasks list.
     *
     * @param taskDescription Description of task to add.
     * @param isDone Tells if this deadline has been completed.
     */
    public void addDeadline(String taskDescription, boolean isDone) {
        try {
            if (taskDescription.length() == 0) {
                throw new DukeException();
            } else {
                int bySize = 3;
                String description = taskDescription.substring(0, taskDescription.indexOf("by") - 2);
                String by = taskDescription.substring(taskDescription.indexOf("by") + bySize);
                Deadline newDeadline = new Deadline(description, by, isDone);
                tasks.add(newDeadline);
                printTaskAddedMessage(newDeadline);
            }
        } catch (DukeException exception) {
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
    }

    /**
     * Adds a todo task to the tasks list.
     *
     * @param taskDescription Description of task to add.
     * @param isDone Tells if this deadline has been completed.
     */
    public void addTodo(String taskDescription, boolean isDone) {
        try {
            if (taskDescription.length() == 0) {
                throw new DukeException();
            } else {
                Todo newTodo = new Todo(taskDescription, isDone);
                tasks.add(newTodo);
                printTaskAddedMessage(newTodo);
            }
        } catch (DukeException exception) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Adds an event task to the tasks list.
     *
     * @param taskDescription Description of task to add.
     * @param isDone Tells if this deadline has been completed.
     */
    public void addEvent(String taskDescription, boolean isDone) {
        try {
            if (taskDescription.length() == 0) {
                throw new DukeException();
            } else {
                String[] dates =  taskDescription.split("/from | /to |from: | to: ");
                String description = dates[0].substring(0, dates[0].length() - 1);
                String fromDate = dates[1];;
                String toDate = dates[2];
                Event newEvent = new Event(description, fromDate, toDate, isDone);
                tasks.add(newEvent);
                printTaskAddedMessage(newEvent);
            }
        } catch (DukeException exception) {
            System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
        }
    }

    /**
     * Deletes a task from the tasks list.
     *
     * @param taskNumToDelete The number of the task in the list to delete.
     */
    public void deleteTask(int taskNumToDelete) {
        Task taskToDelete = tasks.get(taskNumToDelete);
        tasks.remove(taskToDelete);
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskToDelete.toString());
        numTasks--;
        System.out.println("Now you have " + numTasks + " tasks in the list.");
    }

    /**
     * Prints message to tell user task has been added to tasks list.
     *
     * @param task Task that has just been added to tasks list.
     */
    public void printTaskAddedMessage(Task task) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(task.toString());
        numTasks++;
        System.out.println("Now you have " + numTasks + " tasks in the list.");
    }

    /**
     * Returns the list containing all tasks.
     *
     * @return List of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks added to list.
     *
     * @return Number of tasks.
     */
    public int getNumTasks() {
        return numTasks;
    }

    public void findTask(String wordToFind) {
        System.out.println("Here are the matching tasks in your list: ");
        int numTasksFound = 0;
        for (int i = 1; i <= numTasks; i++) {
            Task currTask = tasks.get(i);
            if (currTask.getDescription().contains(wordToFind)) {
                numTasksFound++;
                System.out.println(numTasksFound + ". " + currTask.toString());
            }
        }
    }
}
