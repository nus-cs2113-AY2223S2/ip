import duke.database.Storage;
import duke.exceptions.EmptyInputException;
import duke.exceptions.IllegalInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final String LINE = "____________________________________________________________\n";
    public static final String BYE_MESSAGE = "Bye. Hope to see you again soon!\n";

    public static boolean canExit = false;
    private static Storage database = null;

    /**
     * Prints message after adding To-Do/Deadline/Event
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
                + "\nNow you have " + (myList.size() - 1) + " task(s) in the list\n" + LINE);
    }

    /** Marks task as done or undone and updates database */
    public static void markDoneOrUndone(String currTask, ArrayList<Task> myList) {
        String[] words = currTask.split(" ");
        String firstWord = words[0];
        String taskToMarkOrUnmarkString = currTask.substring(currTask.length() - 1);
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
        String stringToAdd = "";
        for (Task task : myList) {
            stringToAdd += database.stringToWrite(task).toString() + System.lineSeparator();
        }
        try {
            database.writeToFile(stringToAdd);
        } catch (IOException e) {
            System.out.println("Unable to Add xd");
        }
    }

    /**
     * Adds the input text and makes the task as a todo type and updates database
     *
     * @param currTask
     * @param myList
     */
    public static void makeToDoFunction(String currTask, ArrayList<Task> myList) throws EmptyInputException {
        String[] newTask = currTask.split(" ", 2);
        if (newTask[1].isBlank()) {
            throw new EmptyInputException();
        }
        Task toDoTask = new ToDo(newTask[1]);
        myList.add(toDoTask);
        printTaskMessage(myList);
        database.addTaskToDatabase(toDoTask);
    }

    /**
     * Adds the input text and makes the task as a Deadline type and updates database
     *
     * @param currTask
     * @param myList
     */
    public static void makeDeadlinesFunction(String currTask, ArrayList<Task> myList)
            throws EmptyInputException, IllegalInputException {
        if (currTask.contains("/by")) {
            String[] newTask = currTask.split(" ", 2);
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
            database.addTaskToDatabase(deadlineTask);
        } else {
            throw new IllegalInputException();
        }
    }

    /**
     * Adds the input text and makes task as an Event type and updates database
     *
     * @param currTask
     * @param myList
     */
    public static void makeEventFunction(String currTask, ArrayList<Task> myList)
            throws EmptyInputException, IllegalInputException {
        if (currTask.contains("/from") && currTask.contains("/to")) {
            String[] newTask = currTask.split(" ", 2);
            if (newTask[1].isBlank()) {
                throw new EmptyInputException();
            }
            String[] split = newTask[1].split(" /");
            String timeFrom = split[1].substring(5);
            String timeTo = split[2].substring(3);
            Task eventTask = new Event(split[0], timeFrom, timeTo);
            myList.add(eventTask);
            printTaskMessage(myList);
            database.addTaskToDatabase(eventTask);
        } else {
            throw new IllegalInputException();
        }
    }

    public static void handleMarkUnmark(String currTask, ArrayList<Task> myList) {
        try {
            checkMarkUnmark(currTask);
            markDoneOrUndone(currTask, myList);
        } catch (EmptyInputException e) {
            printEmptyInputMessage(currTask);
        } catch (NumberFormatException e) {
            printIllegalInputMessage();
        }
    }

    public static void handleToDo(String currTask, ArrayList<Task> myList) {
        try {
            makeToDoFunction(currTask, myList);
        } catch (ArrayIndexOutOfBoundsException e) {
            printEmptyInputMessage(currTask);
        } catch (EmptyInputException e) {
            printEmptyInputMessage(currTask.trim());
        }
    }

    public static void handleDeadline(String currTask, ArrayList<Task> myList) {
        try {
            makeDeadlinesFunction(currTask, myList);
        } catch (EmptyInputException e) {
            printEmptyInputMessage(currTask.trim());
        } catch (IllegalInputException e) {
            printIllegalInputMessage();
        }
    }

    public static void handleEvent(String currTask, ArrayList<Task> myList) {
        try {
            makeEventFunction(currTask, myList);
        } catch (EmptyInputException e) {
            printEmptyInputMessage(currTask.trim());
        } catch (IllegalInputException e) {
            printIllegalInputMessage();
        }
    }

    public static void checkMarkUnmark(String currTask) throws EmptyInputException {
        String[] list = currTask.split(" ");
        if (list.length < 2) {
            if (list[0].equals("mark") || list[0].equals("unmark")) {
                throw new EmptyInputException();
            }
        }
    }

    /** Deletes the specific task by index */
    public static void deleteTask(String currTask, ArrayList<Task> myList) throws IllegalInputException {
        String[] list = currTask.split(" ");
        if (isNumeric(list[1]) && list.length == 2) {
            int indexToRemove = Integer.parseInt(list[1]) - 1;
            printDeletedMessage(myList, indexToRemove);
            myList.remove(indexToRemove);
        } else {
            throw new IllegalInputException();
        }

        String stringToAdd = "";
        for (Task task : myList) {
            stringToAdd += database.stringToWrite(task).toString() + System.lineSeparator();
        }
        try {
            database.writeToFile(stringToAdd);
        } catch (IOException e) {
            System.out.println("Error encountered when deleting task in memory");
        }
    }

    /** Initialises delete task */
    public static void handleDeleteTask(String currTask, ArrayList<Task> myList) {
        try {
            deleteTask(currTask, myList);
        } catch (IllegalInputException e) {
            printIllegalInputMessage();
        } catch (IndexOutOfBoundsException e) {
            printIllegalInputMessage();
        }
    }

    /** Checks if given string is a number */
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
        try {
            database = new Storage();
        } catch (IOException e) {
            System.out.println("Unable to initialise database");
        }

        ArrayList<Task> myList = database.taskList;
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
                printIllegalInputMessage();
            }
        }
    }
}