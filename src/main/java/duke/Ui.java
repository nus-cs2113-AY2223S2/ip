package duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public static void showHelpMessage() {
        printLine();
        System.out.println("First time using Duke?\n" +
                "No worries! Here is a simple guide for you to get familiar with Duke :)");
        System.out.println("Format: \n" + ">> [todo] <something>, and the system will add a new todo to your list");
        System.out.println("Example: \n" + ">> todo read book\n" + ">> Got it. I've added this task: read book");
        System.out.println("Format: \n" + ">> [event] <something> [from:] <when> [to:] <when>" +
                ", and the system will add a new event to your list");
        System.out.println("Example: \n" + ">> event read book from: 2022-02-02 to: 2022-02-03\n"
                + ">> Got it. I've added this task: read book (from: 2022-02-02, to: 2022-02-03)");
        System.out.println("Format: \n" + ">> [deadline] <something> [by:] <when>" +
                ", and the system will add a new deadline to your list");
        System.out.println("Example: \n" + ">> deadline read book by: 2022-02-02\n"
                + ">> Got it. I've added this task: read book (by: 2022-02-02)");
        System.out.println("Format: \n" + ">> [find] <keyword>" +
                ", and the system will list all items containing keyword in your list");
        System.out.println("Example: \n" + ">> find book\n"
                + ">>  Below are the tasks containing book ...");
        System.out.println("Format: \n" + ">> [mark] <index>" +
                ", and the system will mark the item with the index in list");
        System.out.println("Example: \n" + ">> mark 1 \n"
                + ">> I've marked this task as done ੯•໒꒱❤︎:");
        System.out.println("Format: \n" + ">> [unmark] <index>" +
                ", and the system will unmark the item with the index in list");
        System.out.println("Example: \n" + ">> unmark 1 \n"
                + ">> I've unmarked this task ∪･ω･∪:");
        System.out.println("Format: \n" + ">> [delete] <index>" +
                ", and the system will delete the item with the index in list");
        System.out.println("Example: \n" + ">> delete 1 \n"
                + ">> I've deleted this task ∪･ω･∪:");
        System.out.println("Format: \n" + ">> [bye]" +
                ", and then Duke will say goodbye to you and close the program.");
        System.out.println("Example: \n" + ">> bye \n" + ">> Bye. Hope to see you again soon!ﾉ~");
        System.out.println("Hope it helps!! woof a nice day ੯•໒꒱❤︎");
        printLine();
    }
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
    public static void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLine();
        System.out.println("Hello! I'm Duke  U ´ᴥ` U\n" + "What can I do for you?");
        printLine();
    }
    public static void showInitErrorMessage() {
        System.out.println("No previous file found, Duke will try to create a file to store your data.");
    }
    public static void showGoodByeMessage() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!ﾉ~");
        printLine();
    }
    public static String getUserCommand() {
        Scanner scan = new Scanner(System.in);
        String s;
        s = scan.nextLine();
        while (s.trim().isEmpty() || s.trim().charAt(0) == '#') {
            s = scan.nextLine();
        }
        return s;
    }
    public static void showAddTaskMessage(ArrayList<Task> tasks) {
        printLine();
        System.out.println("Got it. I've added this task: \n" + tasks.get(tasks.size()-1));
        System.out.println("Now you have " + tasks.size() + " tasks in your list. ^ ^");
        printLine();
    }
    public static void printListOfTasks(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i += 1) {
            System.out.print(i + 1);
            System.out.print(". ");
            System.out.println(tasks.get(i));
        }
        printLine();
    }
}