package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private static final Ui ui = new Ui();
    public static ArrayList<Task> tasks;

    /**
     * Constructor to initialise the task list.
     *
     * @param tasks Arraylist used to store the tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Marks a particular task as done.
     * Prints the completed task.
     * Updates the text file based on the task status.
     *
     * @param input Stores the User input.
     * @throws DukeException if the taskNumber is invalid.
     */
    public static void markAsDone(String input) throws DukeException {
        String taskNum = input.substring(input.indexOf(" ") + 1);
        int taskNumber = Integer.parseInt(taskNum);
        if (taskNumber > tasks.size()) {
            throw new DukeException("This task does not exist. Please provide a valid task index.");
        } else {
            ui.printLine();
            tasks.get(taskNumber - 1).markDone();
            System.out.println("Great! I have marked this task as done:");
            System.out.print("\t  ");
            System.out.println(tasks.get(taskNumber - 1));
            ui.printLine();
            Storage.saveTask(tasks);
        }
    }

    /**
     * Marks a particular task as undone.
     * Prints the incomplete task.
     * Updates the text file based on the task status.
     *
     * @param input Stores the User input.
     * @throws DukeException if the taskNumber is invalid.
     */
    public static void markAsUndone(String input) throws DukeException {
        String taskNum = input.substring(input.indexOf(" ") + 1);
        int taskNumber = Integer.parseInt(taskNum);
        if (taskNumber > tasks.size()) {
            throw new DukeException("This task does not exist. Please provide a valid task index.");
        } else {
            ui.printLine();
            tasks.get(taskNumber - 1).markUndone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.print("\t  ");
            System.out.println(tasks.get(taskNumber - 1));
            ui.printLine();
            Storage.saveTask(tasks);
        }
    }

    /**
     * Deletes a particular task.
     * Prints the deleted task.
     * Updates the text file by removing the deleted task.
     *
     * @param input Stores the User input.
     * @throws DukeException if the taskNumber is invalid.
     */
    public static void deleteTask(String input) throws DukeException, IOException {
        String taskNum = input.substring(input.indexOf(" ") + 1);
        int taskNumber = Integer.parseInt(taskNum);
        if (taskNumber > tasks.size()) {
            throw new DukeException("This task does not exist. Please provide a valid task index.");
        } else {
            ui.printLine();
            Task deletedTask = tasks.get(taskNumber - 1);
            tasks.remove(taskNumber - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.print("\t  ");
            System.out.println(deletedTask);
            System.out.println("Now you have " + tasks.size() + " tasks in your list.");
            ui.printLine();
            Storage.saveTask(tasks);
        }
    }

    /**
     * Displays all the tasks in the task list to the user.
     *
     * @throws DukeException if the task list is empty.
     */
    public static void printList() throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException("Sorry, there are no tasks in the list currently.");
        } else {
            ui.printLine();
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.print("\t" + (i + 1) + ".");
                System.out.println(tasks.get(i));
            }
        }
        ui.printLine();
    }

    /**
     * Adds the todo to task list.
     *
     * @param input Stores the user input.
     * @throws DukeException if the description is empty.
     */
    public static void addTodo(String input) throws DukeException {
        String[] words = input.split(" ");
        if (words.length < 2) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else {
            Todo todo = new Todo(input.substring(5));
            tasks.add(todo);
            ui.printLine();
            System.out.println("Got it. I've added this task:");
            System.out.print("\t  ");
            System.out.println(todo);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            ui.printLine();
            Storage.saveTask(tasks);
        }
    }

    /**
     * Adds the deadline to task list.
     *
     * @param input Stores the user input.
     * @throws DukeException if the description or date is empty.
     */
    public static void addDeadline(String input) throws DukeException {
        String[] words = input.split(" ");
        int index = input.indexOf("/");
        if (words.length < 2 || index == -1) {
            throw new DukeException("☹ OOPS!!! The description or date of a deadline cannot be empty.");
        } else {
            String[] wordDeadline = input.substring(9).split("/");
            Deadline deadline = new Deadline(wordDeadline[0].trim(), wordDeadline[1].substring(3));
            tasks.add(deadline);
            ui.printLine();
            System.out.println("Got it. I've added this task:");
            System.out.print("\t  ");
            System.out.println(deadline);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            ui.printLine();
            Storage.saveTask(tasks);
        }
    }

    /**
     * Adds the event to task list.
     *
     * @param input Stores the user input.
     * @throws DukeException if the description or time is empty.
     */
    public static void addEvent(String input) throws DukeException {
        String[] words = input.split(" ");
        int index = input.indexOf("/");
        if (words.length < 2 || index == -1) {
            throw new DukeException("☹ OOPS!!! The description or time of an event cannot be empty.");
        } else {
            String[] wordEvent = input.substring(5).split("/", 3);
            Event event = new Event(wordEvent[0].trim(), wordEvent[1].substring(5).trim(), wordEvent[2].substring(3).trim());
            tasks.add(event);
            ui.printLine();
            System.out.println("Got it. I've added this task:");
            System.out.print("\t  ");
            System.out.println(event);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            ui.printLine();
            Storage.saveTask(tasks);
        }
    }

    /**
     * Find a task in the task list that contains the text specified by the user.
     *
     * @param input Stores the user input.
     */
    public static void findTask(String input) {
        int currentIndex = 1;
        ui.printLine();
        System.out.println("Here are the matching tasks in your list:");

        for (Task task : tasks) {
            if (task.description.contains(input.substring(5))) {
                System.out.println("\t" + currentIndex + "." + task);
                currentIndex++;
            }
        }
        ui.printLine();
    }
}
