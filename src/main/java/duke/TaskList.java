package duke;

import duke.commands.Ui;
import duke.exceptions.IncorrectDeadlineException;
import duke.exceptions.IncorrectEventException;

import java.util.ArrayList;

public abstract class TaskList{

    // ArrayList used to store a list of tasks
    private static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * This is a getter for ArrayList
     *
     * @return a list of tasks
     */
    public static ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * Retrieves the current size of the list
     *
     * @return an integer value representing the size of the list
     */
    public static int getTaskSize() {
        return tasks.size();
    }

    /**
     * Prints all the task in the list when the user sends a "list" command
     */
    public static void printTaskList() {
        for (int i = 0; i < getTaskSize(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
    }

    /**
     * Creates and adds a new todo task into the tasks ArrayList and informs the user that the task has been created
     *
     * @param command the description of the todo task that will be added
     */
    public static void addTodo (String command) {
        Todo todo = new Todo(command);
        tasks.add(todo);
        Ui.printTask(todo.toString(), TaskList.getTaskSize());
    }

    /**
     * Creates and adds a new deadline task into the tasks ArrayList and informs the user that the task has been created
     *
     * @param command the description of the deadline task that will be added
     * @throws IncorrectDeadlineException the task description is not in the correct format
     */
    public static void addDeadline (String command) throws IncorrectDeadlineException {
        if (command.indexOf("/by") == -1) {
                throw new IncorrectDeadlineException();
        }
        String[] message = command.split(" /by", 2);
        Deadline deadline = new Deadline(message[0], message[1]);
        tasks.add(deadline);
        Ui.printTask(deadline.toString(), TaskList.getTaskSize());
    }

    /**
     * Creates and adds a new event task into the tasks ArrayList and informs the user that the task has been created
     *
     * @param command the description of the event task that will be added
     * @throws IncorrectDeadlineException the task description is not in the correct format
     */
    public static void addEvent(String command) throws IncorrectEventException {
        if (command.indexOf("/from") == -1 || command.indexOf("/to") == -1) {
            throw new IncorrectEventException();
        }
        String[] message = command.split(" /from");
        String[] period = message[1].split(" /to");
        Event event = new Event(message[0], period[0], period[1]);
        tasks.add(event);
        Ui.printTask(event.toString(), TaskList.getTaskSize());
    }

    /**
     * Deletes a task at an index specified by the user in the list of tasks
     *
     * @param index the number of the task in the list
     */
    public static void deleteTask(int index) {
        Ui.printRemoval(tasks.get(index - 1).toString(), TaskList.getTaskSize() - 1);
        tasks.remove(index);
    }

    /**
     * Marks a task at an index specified by the user in the list of tasks as uncompleted and sends a message
     * to inform the user that this action has been performed successfully
     *
     * @param index the number of the task in the list
     */
    public static void markNotDone(int index) {
        tasks.get(index - 1).markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(index - 1).printTask());
    }

    /**
     * Marks a task at an index specified by the user in the list of tasks as completed and sends a message
     *      * to inform the user that this action has been performed successfully
     *
     * @param index the number of the task in the list
     */
    public static void markDone(int index) {
        tasks.get(index - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index - 1).printTask());
    }
}
