import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static String LINE_PRINT = "____________________________________________________________\n";

    /**
     * Adds the input text into the list
     *
     * @param t
     * @param myList
     */
    public static void addList(Task t, ArrayList<Task> myList) {
        myList.add(t);
    }

    /**
     * Prints all the items in the list
     *
     * @param myList
     */
    public static void printList(ArrayList<Task> myList) {
        System.out.println(LINE_PRINT + "Here are the tasks in your list: \n");
        for (int i = 0; i < myList.size(); ++i) {
            System.out.println(i + 1 + ". " + myList.get(i));
        }
        System.out.println(LINE_PRINT);
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
        System.out.println(LINE_PRINT
                + "Nice! I've marked this task as done:\n"
                + myList.get(taskToMark).description + "\n"
                + LINE_PRINT);
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
        //myList.set(taskToUnmark, myList.get(taskToUnmark).replaceFirst("X", " "));
        System.out.println(LINE_PRINT
                + "OK, I've marked this task as not done yet:\n"
                + myList.get(taskToUnmark) + "\n"
                + LINE_PRINT);
    }

    /**
     * Adds the input text and marks the task as a To-Do
     *
     * @param s
     * @param myList
     */
    public static void toDoFunction(String s, ArrayList<Task> myList) {
        String newTask[] = s.split(" ", 2);
        ToDo toDoTask = new ToDo(newTask[1]);
        myList.add(toDoTask);
        System.out.println(LINE_PRINT
                + "Got it. I've added this task:\n"
                + myList.get(myList.size() - 1) + "\n"
                + "Now you have " + myList.size() + " in the list.\n"
                + LINE_PRINT);
    }

    /**
     * Adds the input text and marks the task as a Deadline
     *
     * @param s
     * @param myList
     */
    public static void deadlinesFunction(String s, ArrayList<Task> myList) {
        String newTask[] = s.split(" ", 2);
        String split[] = newTask[1].split(" /by ");
        Deadline deadlineTask = new Deadline(split[0], split[1]);
        myList.add(deadlineTask);
        System.out.println(LINE_PRINT
                + "Got it. I've added this task:\n"
                + myList.get(myList.size() - 1) + "\n"
                + "Now you have " + myList.size() + " in the list.\n"
                + LINE_PRINT);
    }

    /**
     * Adds the input text and marks the task as an Event
     *
     * @param s
     * @param myList
     */
    public static void eventFunction(String s, ArrayList<Task> myList) {
        String newTask[] = s.split(" ", 2);
        String split[] = newTask[1].split(" /");
        String timeFrom = split[1].substring(5);
        String timeTo = split[2].substring(3);
        Event eventTask = new Event(split[0], timeFrom, timeTo);
        myList.add(eventTask);
        System.out.println(LINE_PRINT
                + "Got it. I've added this task:\n"
                + myList.get(myList.size() - 1) + "\n"
                + "Now you have " + myList.size() + " in the list.\n"
                + LINE_PRINT);
    }

    public static void main(String[] args) {
        String logo = "____________________________________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        String byeLine = "Bye. Hope to see you again soon!\n";
        System.out.println(logo);
        boolean canExit = false;
        ArrayList<Task> myList = new ArrayList<Task>();

        while (!canExit) {
            Scanner in = new Scanner(System.in);
            String s = in.nextLine();
            if (s.toLowerCase().equals("bye")) {
                System.out.println(byeLine);
                canExit = true;
            } else if (s.toLowerCase().equals("list")) {
                printList(myList);
            } else if (s.toLowerCase().startsWith("mark")) {
                markDone(s, myList);
            } else if (s.toLowerCase().startsWith("unmark")) {
                markUndone(s, myList);
            } else if (s.toLowerCase().startsWith("todo")) {
                toDoFunction(s, myList);
            } else if (s.toLowerCase().startsWith("deadline")) {
                deadlinesFunction(s, myList);
            } else if (s.toLowerCase().startsWith("event")) {
                eventFunction(s, myList);
            } else {
                Task t = new Task(s);
                addList(t, myList);
                System.out.println(LINE_PRINT + "added: " + t.description + "\n" + LINE_PRINT);
            }
        }
    }
}