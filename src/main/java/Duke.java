import duke.task.Task;
import duke.task.ToDo;
import duke.task.Event;
import duke.task.Deadline;
import duke.exceptions.DukeException;
import duke.exceptions.EmptyInputException;
import duke.exceptions.IllegalInputException;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final String LINE = "____________________________________________________________\n";
    public static final String BYE_MESSAGE = "Bye. Hope to see you again soon!\n";
    public static boolean canExit = false;

    /**
     * Prints message after adding To-Do/Deadline/Event duke.task
     *
     * @param myList
     */
    public static void printTaskMessage(ArrayList<Task> myList) {
        System.out.println(LINE
                + "Got it. I've added this duke.task:\n"
                + myList.get(myList.size() - 1) + "\n"
                + "Now you have " + myList.size() + " in the list.\n"
                + LINE);
    }

    /**
     * Prints a greeting user message
     */
    public static void printGreetMessage() {
        System.out.println(LINE + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + LINE);
    }

    /**
     * Prints exiting message before program stops
     */
    public static void printByeMessage() {
        System.out.println(BYE_MESSAGE);
    }

    /**
     * Prints an added message
     *
     * @param myList
     */
    public static void printAddedTaskMessage(ArrayList<Task> myList) {
        System.out.println(LINE + "added: "
                + myList.get(myList.size() - 1).getDescription()
                + "\n" + LINE);
    }

    public static void printIllegalInputMessage() {
        System.out.println(LINE
                + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                + LINE);
    }

    public static void printEmptyInputMessage(String task) {
        System.out.println(LINE
                + "☹ OOPS!!! The description of " + task + " cannot be empty.\n"
                + LINE);
    }

    /**
     * Adds the input text into the list
     *
     * @param s
     * @param myList
     */
    public static void addList(String s, ArrayList<Task> myList) {
        Task t = new Task(s);
        myList.add(t);
    }

    /**
     * Prints all the items in the list
     *
     * @param myList
     */
    public static void printList(ArrayList<Task> myList) {
        System.out.println(LINE + "Here are the tasks in your list: \n");
        for (int i = 0; i < myList.size(); ++i) {
            System.out.println(i + 1 + ". " + myList.get(i));
        }
        System.out.println(LINE);
    }

    public static void printDeletedMessage(ArrayList<Task> myList, int index) {
        System.out.println(LINE + "Noted. I've removed this task:\n" + myList.get(index)
                + "\nNow you have " + (myList.size()-1) + " task(s) in the list\n" + LINE);
    }

    /**
     * Marks the given item in the list with an "X"
     *
     * @param s
     * @param myList
     */
    public static void markDone(String s, ArrayList<Task> myList) {
        String taskToMarkString = s.substring(s.length() - 1);
        int taskToMark = Integer.parseInt(taskToMarkString) - 1;
        myList.get(taskToMark).setDone();
        System.out.println(LINE
                + "Nice! I've marked this duke.task as done:\n"
                + myList.get(taskToMark) + "\n"
                + LINE);
    }

    /**
     * Unmarks the given item in the list
     *
     * @param s
     * @param myList
     */
    public static void markUndone(String s, ArrayList<Task> myList) {
        String taskToUnmarkString = s.substring(s.length() - 1);
        int taskToUnmark = Integer.parseInt(taskToUnmarkString) - 1;
        myList.get(taskToUnmark).setUndone();
        System.out.println(LINE
                + "OK, I've marked this duke.task as not done yet:\n"
                + myList.get(taskToUnmark) + "\n"
                + LINE);
    }

    public static void markDoneOrUndone(String s, ArrayList<Task> myList) {
        String[] words = s.split(" ");
        String firstWord = words[0];
        String taskToMarkOrUnmarkString = s.substring(s.length() - 1);
        int taskToMarkOrUnmark = Integer.parseInt(taskToMarkOrUnmarkString) - 1;
        if (firstWord.equals("mark")) {
            myList.get(taskToMarkOrUnmark).setDone();
            System.out.println(LINE
                    + "Nice! I've marked this duke.task as done:\n"
                    + myList.get(taskToMarkOrUnmark) + "\n"
                    + LINE);
        } else {
            myList.get(taskToMarkOrUnmark).setUndone();
            System.out.println(LINE
                    + "OK, I've marked this duke.task as not done yet:\n"
                    + myList.get(taskToMarkOrUnmark) + "\n"
                    + LINE);
        }
    }

    /**
     * Adds the input text and marks the duke.task as a To-Do
     *
     * @param s
     * @param myList
     */
    public static void makeToDoFunction(String s, ArrayList<Task> myList) throws EmptyInputException {
        String[] newTask = s.split(" ", 2);
        if (newTask[1].isBlank()) {
            throw new EmptyInputException();
        }
        Task toDoTask = new ToDo(newTask[1]);
        myList.add(toDoTask);
        printTaskMessage(myList);
    }

    /**
     * Adds the input text and marks the duke.task as a Deadline
     *
     * @param s
     * @param myList
     */
    public static void makeDeadlinesFunction(String s, ArrayList<Task> myList)
            throws EmptyInputException, IllegalInputException {
        if (s.contains("/by")) {
            String[] newTask = s.split(" ", 2);
            if (newTask[1].isBlank()) {
                throw new EmptyInputException();
            }
            String[] split = newTask[1].split(" /by ");
            if (split[1].isBlank()) {
                throw new EmptyInputException();
            }
            Task deadlineTask = new Deadline(split[0], split[1]);
            myList.add(deadlineTask);
            printTaskMessage(myList);
        } else {
            throw new IllegalInputException();
        }
    }

    /**
     * Adds the input text and marks the duke.task as an Event
     *
     * @param s
     * @param myList
     */
    public static void makeEventFunction(String s, ArrayList<Task> myList)
            throws EmptyInputException, IllegalInputException {
        if (s.contains("/from") && s.contains("/to")) {
            String[] newTask = s.split(" ", 2);
            if (newTask[1].isBlank()) {
                throw new EmptyInputException();
            }
            String[] split = newTask[1].split(" /");
            String timeFrom = split[1].substring(5);
            String timeTo = split[2].substring(3);
            Task eventTask = new Event(split[0], timeFrom, timeTo);
            myList.add(eventTask);
            printTaskMessage(myList);
        } else {
            throw new IllegalInputException();
        }
    }

    public static void handleMarkUnmark(String s, ArrayList<Task> myList) {
        try {
            checkMarkUnmark(s);
            markDoneOrUndone(s, myList);
        } catch (EmptyInputException e) {
            printEmptyInputMessage(s);
        } catch (NumberFormatException e) {
            printIllegalInputMessage();
        }
    }

    public static void handleToDo(String s, ArrayList<Task> myList) {
        try {
            makeToDoFunction(s, myList);
        } catch (ArrayIndexOutOfBoundsException e) {
            printEmptyInputMessage(s);
        } catch (EmptyInputException e) {
            printEmptyInputMessage(s.trim());
        }
    }

    public static void handleDeadline(String s, ArrayList<Task> myList) {
        try {
            makeDeadlinesFunction(s, myList);
        } catch (EmptyInputException e) {
            printEmptyInputMessage(s.trim());
        } catch (IllegalInputException e) {
            printIllegalInputMessage();
        }
    }

    public static void handleEvent(String s, ArrayList<Task> myList) {
        try {
            makeEventFunction(s, myList);
        } catch (EmptyInputException e) {
            printEmptyInputMessage(s.trim());
        } catch (IllegalInputException e) {
            printIllegalInputMessage();
        }
    }

    public static void checkMarkUnmark(String s) throws EmptyInputException {
        String[] list = s.split(" ");
        if (list.length < 2) {
            if (list[0].equals("mark") || list[0].equals("unmark")) {
                throw new EmptyInputException();
            }
        }
    }

    public static void deleteTask(String s, ArrayList<Task> myList) throws IllegalInputException {
        String[] list = s.split(" ");
        if (isNumeric(list[1]) && list.length == 2) {
            int indexToRemove = Integer.parseInt(list[1]) - 1;
            printDeletedMessage(myList, indexToRemove);
            myList.remove(indexToRemove);
        } else {
            throw new IllegalInputException();
        }
    }

    public static void handleDeleteTask(String s, ArrayList<Task> myList) {
        try {
            deleteTask(s, myList);
        } catch (IllegalInputException e) {
            printIllegalInputMessage();
        } catch (IndexOutOfBoundsException e) {
            printIllegalInputMessage();
        }
    }

    public static boolean isNumeric(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        printGreetMessage();
        ArrayList<Task> myList = new ArrayList<Task>();

        while (!canExit) {
            Scanner in = new Scanner(System.in);
            String s = in.nextLine();
            if (s.toLowerCase().equals("bye")) {
                printByeMessage();
                canExit = true;
            } else if (s.toLowerCase().equals("list")) {
                printList(myList);
            } else if (s.toLowerCase().startsWith("mark") || s.toLowerCase().startsWith("unmark")) {
                handleMarkUnmark(s, myList);
            } else if (s.toLowerCase().startsWith("todo")) {
                handleToDo(s, myList);
            } else if (s.toLowerCase().startsWith("deadline")) {
                handleDeadline(s, myList);
            } else if (s.toLowerCase().startsWith("event")) {
                handleEvent(s, myList);
            } else if (s.toLowerCase().startsWith("delete")) {
                handleDeleteTask(s, myList);
            } else {
                //addList(s, myList);
                //printAddedTaskMessage(myList);
                printIllegalInputMessage();
            }
        }
    }
}