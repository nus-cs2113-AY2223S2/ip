package task;

import java.util.ArrayList;
import exceptions.DukeException;

import static ui.UI.greet;

public class TaskList {
    private static final String LINE = "____________________________________________________________";
    private static final String TODO_ERROR = "☹ OOPS!!! The description of a todo cannot be empty.";
    public static ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<Task>();
    }

    /**
     * Delete the task from the list indicated by the index argument from the user input. The index parameter is
     * the index of the task in the list.
     * @param index
     */
    public static void deleteTask(int index) {
        System.out.println(LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + list.get(index).toString());
        list.remove(index);
        int size = list.size();
        System.out.println("Now you have " + size + " tasks in the list");
    }

    /**
     * Mark the indicated task to be done in the list. The index parameter is the index of the task in the list.
     * @param index
     */
    public static void markTask(int index) {
        System.out.println(LINE);
        list.get(index).markDone();
        System.out.println("Awesome! I've mark this task as done:");
        System.out.println("[" + list.get(index).getStatusIcon() + "] " + list.get(index).description.split(" ", 2)[1]);
        System.out.println(LINE);
    }

    /**
     * Mark the indicated task to be not done in the list. The index parameter is the index of the task in the list.
     * @param index
     */
    public static void unmarkTask(int index) {
        System.out.println(LINE);
        list.get(index).markUndone();
        System.out.println("What!?!? OK, I've marked this task as not done yet:");
        System.out.println("[" + list.get(index).getStatusIcon() + "] " + list.get(index).description.split(" ", 2)[1]);
        System.out.println(LINE);
    }

    /**
     * Create and add a TodoTask into the list. Print the confirmation of the added task.
     * The input parameter is the TodoTask description typed by the user.
     * @param input
     * @throws DukeException if length of task description less than 2
     */
    public static void addTodo(String input) throws DukeException {
        try {
            Todo task = new Todo(input);
            task.setTaskType("T");
            list.add(task);
            if (task.description.split(" ").length < 2) {
                throw new DukeException(TODO_ERROR);
            } else {
                System.out.println(LINE);
                System.out.println("Roger! The Todo task has been added: \n" + task.toString());
                System.out.println("Now you have " + list.size() + " in the list");
                System.out.println(LINE);
            }
        } catch (DukeException e) {
            printError(e);
        }
    }

    /**
     * Create and add a Deadline Task into the list. Print the confirmation of the added task.
     * The input parameter is the Deadline Task description.
     * @param input
     */
    public static void addDeadline(String input) {
        Deadline task = new Deadline(input);
        task.setTaskType("D");
        list.add(task);
        System.out.println(LINE);
        System.out.println("Roger! The Deadline task has been added: \n    " + task.toString());
        System.out.println("Now you have " + list.size() + " in the list");
        System.out.println(LINE);
    }

    /**
     * Create and add a Event Task into the list. Print the confirmation of the added task.
     * The input parameter is the Event Task description.
     * @param input
     */
    public static void addEvent(String input) {
        Event task = new Event(input);
        task.setTaskType("E");
        list.add(task);
        System.out.println(LINE);
        System.out.println("Roger! The Deadline task has been added: \n    " + task.toString());
        System.out.println("Now you have " + list.size() + " in the list");
        System.out.println(LINE);
    }

    /**
     * Create and add existing TodoTask from the save file into the list. The input parameter is the saved task
     * description data from the save file. The status parameter is to determine whether saved task is mark done
     * mark undone.
     * @param input
     * @param status
     */
    public static void addTodoData(String input, String status) {
            Todo task = new Todo(input);
            if (status.equals("X")) {
                task.markDone();
            }
            task.setTaskType("T");
            list.add(task);
    }

    /**
     * Create and add existing Deadline Task from the save file into the list. The input parameter is the saved task
     * description data from the save file. The status parameter is to determine whether saved task is mark done
     * mark undone.
     * @param input
     * @param status
     */
    public static void addDeadlineData(String input, String status) {
        Deadline task = new Deadline(input);
        if (status.equals("X")) {
            task.markDone();
        }
        task.setTaskType("D");
        list.add(task);
    }

    /**
     * Create and add existing Event Task from the save file into the list. The input parameter is the saved task
     * description data from the save file. The status parameter is to determine whether saved task is mark done
     * mark undone.
     * @param input
     * @param status
     */
    public static void addEventData(String input, String status) {
        Event task = new Event(input);
        if (status.equals("X")) {
            task.markDone();
        }
        task.setTaskType("E");
        list.add(task);
    }

    /**
     * Print out all the task in the list. If the list is empty, print a text "No task added yet".
     */
    public static void printList() {
        System.out.println(LINE);
        int numTask = list.size();
        if (numTask == 0) {
            System.out.println("No task added yet");
            System.out.println(LINE);
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i+1) + "." + list.get(i).toString());
            }
            System.out.println(LINE);
        }
    }

    /**
     * This method prints out the previous tasks stored in the save file to show the
     * user what they had saved from their previous usage of Duke.
     */
    public static void loadSaveList() {
        System.out.println(LINE);
        int numTask = list.size();
        if (numTask == 0) {
            System.out.println("No saved task recorded");
            System.out.println(LINE);
        } else {
            System.out.println("Here are the tasks in your save list:");
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i+1) + "." + list.get(i).toString());
            }
            System.out.println(LINE);
        }
    }

    /**
     * This method prints out the found tasks in the list to show the user
     * which tasks were found from their find input.
     */
    private static void printFoundList(ArrayList<Task> foundTasks) {
        System.out.println(LINE);
        int numTask = foundTasks.size();
        if (numTask == 0) {
            System.out.println("No task found");
            System.out.println(LINE);
        } else {
            System.out.println("Here are the matching tasks in your save list:");
            for (int i = 0; i < foundTasks.size(); i++) {
                System.out.println((i+1) + "." + foundTasks.get(i).toString());
            }
            System.out.println(LINE);
        }
    }

    /**
     * Stores the task containing the input keyed by the user into a list.
     * @param input
     */
    public static void findTask(String input) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for(Task t : list) {
            String task = t.description;
            if (task.contains(input)) {
                foundTasks.add(t);
            }
        }
        printFoundList(foundTasks);
    }

    public static void printError(DukeException e) {
        System.out.println(LINE + System.lineSeparator() + e.getMessage() + System.lineSeparator() + LINE );
    }
}
