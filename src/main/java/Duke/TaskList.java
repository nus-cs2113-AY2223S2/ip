package Duke;

import Duke.Tasks.Task;
import Duke.Tasks.Todo;
import Duke.Tasks.Event;
import Duke.Tasks.Deadline;

/**
 * Implement methods that operate on the tasks in the task list.
 * Including add tasks, delete task, find task, mark and unmark a task.
 */
public class TaskList {
    public static void printAddMessage(Task[] Tasks, int taskIndex) {
        System.out.println("--------------------------------");
        System.out.println("Got it. I've added this task:");
        System.out.println(Tasks[taskIndex].toString());
        System.out.println("Now you have " + (taskIndex + 1) + " task(s) in the list");
        System.out.println("--------------------------------");
    }

    /**
     * Add a Todo task.
     * Print the message of adding a Todo.
     *
     * @param list    The task list.
     * @param command The input message that needs to be further divided.
     * @param index   The number of tasks in the list.
     */
    public static void addTodo(Task[] list, String[] command, int index) {
        list[index] = new Todo(command[1]);
        printAddMessage(list, index);
    }

    /**
     * Split the input command to description, start time and end time
     * Add a new event to the list
     *
     * @param list    The task list.
     * @param command The input message that needs to be further divided.
     * @param index   The number of tasks in the list.
     */
    public static void addEvent(Task[] list, String[] command, int index) {
        String[] description = command[1].split(" /from ");
        String[] dates = description[1].split(" /to ");
        String start = dates[0];
        String end = dates[1];
        list[index] = new Event(description[0], start, end);
        printAddMessage(list, index);
    }

    /**
     * Split the input command to description and deadline time
     * Add a new deadline to the list
     *
     * @param list    The task list.
     * @param command The input message that needs to be further divided.
     * @param index   The number of tasks in the list.
     */
    public static void addDeadline(Task[] list, String[] command, int index) {
        String[] description = command[1].split(" /by ");
        String ddl = description[1];
        list[index] = new Deadline(description[0], ddl);
        printAddMessage(list, index);
    }

    /**
     * Find the index of the command that you want to delete based on the input command
     * Delete the task
     *
     * @param list    The task list.
     * @param command The input message that needs to be further divided.
     * @param index   The number of tasks in the list.
     */
    public static void deleteTask(Task[] list, String[] command, int index) {

        String number = command[1];
        int deleteIndex = Integer.parseInt(number);
        if (deleteIndex == index) {
            System.out.println("--------------------------------");
            System.out.println("Noted. I've removed this task:");
            System.out.println(list[deleteIndex - 1].toString());
            System.out.println("Now you have " + (index - 1) + " task(s) in the list");
            System.out.println("--------------------------------");
        } else if (deleteIndex >= 1 && deleteIndex <= index - 1) {
            System.out.println("--------------------------------");
            System.out.println("Noted. I've removed this task:");
            System.out.println(list[deleteIndex - 1].toString());
            System.out.println("Now you have " + (index - 1) + " task(s) in the list");
            System.out.println("--------------------------------");
            for (int i = deleteIndex - 1; i < index - 1; i++) {
                list[i] = list[i + 1];
            }
        } else {
            System.out.println("Oops! Delete should be followed by a valid number. ");
        }

    }

    /**
     * Find the index of the command that you want to mark based on the input command
     * Set the task's isDone to true
     *
     * @param list    The task list.
     * @param command The input message that needs to be further divided.
     */
    public static void markTask(Task[] list, String[] command) {
        try {
            String number = command[1];
            int markIndex = Integer.parseInt(number);
            list[markIndex - 1].isDone = true;
            System.out.println("--------------------------------");
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("\t" + list[markIndex - 1].toString());
            System.out.println("--------------------------------");
        } catch (NumberFormatException e) {
            System.out.println("Oops! Mark should be followed by a number. " +
                    "(A valid index number should be separated by a space after the mark)");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Oops! Mark index out of bound! " +
                    "(A valid index number should be separated by a space after the mark)");
        } catch (NullPointerException e) {
            System.out.println("Oops! Mark index out of bound! " +
                    "(A valid index number should be separated by a space after the mark)");
        }
    }

    /**
     * Find the index of the command that you want to unmark based on the input command
     * Set the task's isDone to false
     *
     * @param list    The task list.
     * @param command The input message that needs to be further divided.
     */
    public static void unmarkTask(Task[] list, String[] command) {
        try {
            String number = command[1];
            int unMarkIndex = Integer.parseInt(number);
            list[unMarkIndex - 1].isDone = false;
            System.out.println("--------------------------------");
            System.out.println("Nice! I've marked this task as not done yet:");
            System.out.println("\t" + list[unMarkIndex - 1].toString());
            System.out.println("--------------------------------");
        } catch (NumberFormatException e) {
            System.out.println("Oops! Unmark should be followed by a number. " +
                    "(A valid index number should be separated by a space after the unmark)");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Oops! Unmark index out of bound! " +
                    "(A valid index number should be separated by a space after the unmark)");
        } catch (NullPointerException e) {
            System.out.println("Oops! Unmark index out of bound! " +
                    "(A valid index number should be separated by a space after the unmark)");
        }
    }

    /**
     * Find the task that contains the task name in the task list.
     * If the nothing is found, print out the not found message.
     *
     * @param list       this is the task list where we find the task
     * @param whatToFind this is the task name that we want to find
     * @param index      this is the number of tasks in the task list
     */
    public static void findTask(Task[] list, String whatToFind, int index) {
        boolean contain = false;
        for (int i = 0; i < index; i++) {
            if (list[i].toString().substring(6).contains(whatToFind)) {
                System.out.println("Here are the matching task in your list:");
                System.out.println("\t" + list[i].toString());
                contain = true;
            }
        }
        if (!contain) {
            System.out.println("--------------------------------");
            System.out.println("Sorry I don't find any content related in the list.");
            System.out.println("--------------------------------");
        }
    }
}
