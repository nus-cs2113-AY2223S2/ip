package duke;

import duke.exceptions.EmptyFindException;
import duke.exceptions.MissingDescriptionException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    public static final String LINE = "    ____________________________________________________________\n";
    public static final String NOT_DONE = "    OK :(, I've marked this task as not done yet: \n    ";
    public static final String DONE = "    Nice! I've marked this task as done: \n    ";
    public TaskList() {}

    /**
     * Checks through every task the user has input in the task list for matches with
     * the string input by user. Stores all matching tasks in a matchingTasks list and
     * print out the list.
     * @param tasks the list of tasks that the user input.
     * @param words the input command of the user, the first element is the string "find",
     *              the second element is the string the user wants to find.
     */
    public static void findString (ArrayList<Task> tasks, String[] words) throws EmptyFindException {
        String toFind = words[1];
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task x : tasks) {
            if (x.description.contains(toFind)){
                matchingTasks.add(x);
            }
        }
        Ui.printMatchingList(matchingTasks);
    }

    /**
     * Sets the completion status of the task based on its index number
     * in the list as well as the user command.
     * @param tasks the task list.
     * @param words the task description.
     * @param command the command given by user.
     */
    public static void editMarkStatus(ArrayList<Task> tasks, String[] words, String command) {
        int index = Integer.parseInt(words[1]) - 1;
        if (command.equals("unmark")) {
            tasks.get(index).setDone(false);
            System.out.print(LINE + NOT_DONE);
        } else {
            tasks.get(index).setDone(true);
            System.out.print(LINE + DONE);
        }
        System.out.print(tasks.get(index).toString() + "\n" + LINE);
    }

    /**
     * Adds an event item to the task list.
     * @param tasks the task list.
     * @param words the task description.
     * @throws MissingDescriptionException Missing parameters.
     */
    public static void addEvent(ArrayList<Task> tasks, String[] words) throws MissingDescriptionException {
        Event event = Parser.parseEvent(words);
        tasks.add(event);
    }

    /**
     * Adds a deadline item to the task list.
     * @param tasks the task list.
     * @param words the task description.
     * @throws MissingDescriptionException Missing parameters.
     */
    public static void addDeadline(ArrayList<Task> tasks, String[] words) throws MissingDescriptionException {
        Deadline deadline = Parser.parseDeadline(words);
        tasks.add(deadline);
    }

    /**
     * Adds a todo item to the task list.
     * @param tasks the task list.
     * @param words the task description.
     * @throws MissingDescriptionException Missing parameters.
     */
    public static void addTodo(ArrayList<Task> tasks, String[] words) throws MissingDescriptionException {
        Task todo = Parser.parseTodo(words);
        tasks.add(todo);
    }

    /**
     * Deletes a task from the task list.
     * @param tasks the task list.
     * @param words the task description.
     */
    public static void deleteTask(ArrayList<Task> tasks, String[] words) {
        int index = Integer.parseInt(words[1]) - 1;
        System.out.print(LINE + "    Noted. I've removed this task:\n      " +
                tasks.get(index).toString());
        tasks.remove(index);
        System.out.print("\n    Now you have " + tasks.size() + " tasks in the list.\n" +
                LINE);
    }
}
