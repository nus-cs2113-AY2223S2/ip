import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static String LINE_PRINT = "____________________________________________________________\n";

    /**
     * Adds the text entered by the user into the list and prints the added text
     *
     * @param s
     * @param myList
     */
    public static void addList(String s, ArrayList<String> myList) {
        myList.add("[ ] " + s);
        System.out.println(LINE_PRINT + "added: " + s + "\n" + LINE_PRINT);
    }

    /**
     * Prints all the items in the list
     *
     * @param myList
     */
    public static void printList(ArrayList<String> myList) {
        System.out.println(LINE_PRINT);
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
    public static void markDone(String s, ArrayList<String> myList) {
        String taskToMarkString = s.substring(s.length() - 1);
        int taskToMark = Integer.parseInt(taskToMarkString) - 1;
        myList.set(taskToMark, myList.get(taskToMark).replaceFirst(" ", "X"));
        System.out.println(LINE_PRINT
                + "Nice! I've marked this task as done:\n"
                + myList.get(taskToMark) + "\n"
                + LINE_PRINT);
    }

    /**
     * Unmarks the given item in the list
     *
     * @param s
     * @param myList
     */
    public static void markUndone(String s, ArrayList<String> myList) {
        String taskToUnmarkString = s.substring(s.length() - 1);
        int taskToUnmark = Integer.parseInt(taskToUnmarkString) - 1;
        myList.set(taskToUnmark, myList.get(taskToUnmark).replaceFirst("X", " "));
        System.out.println(LINE_PRINT
                + "OK, I've marked this task as not done yet:\n"
                + myList.get(taskToUnmark) + "\n"
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
        ArrayList<String> myList = new ArrayList<String>();

        while (!canExit) {
            Scanner in = new Scanner(System.in);
            String s = in.nextLine();
            String sArray[] = s.split(" ");
            String firstWord = sArray[0];

            if (s.toLowerCase().equals("bye")) {
                System.out.println(byeLine);
                canExit = true;
            } else if (s.toLowerCase().equals("list")) {
                printList(myList);
            } else if (firstWord.toLowerCase().equals("mark")) {
                markDone(s, myList);
            } else if (firstWord.toLowerCase().equals("unmark")) {
                markUndone(s, myList);
            } else {
                addList(s, myList);
            }
        }
    }
}