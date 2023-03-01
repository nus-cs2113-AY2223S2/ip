package utility;

import java.util.ArrayList;

import task.Task;

public class Ui {
    final static String HORIZONTAL_RULE = "____________________________________________________________\n";

    public static void breakLine() {
        System.out.println(HORIZONTAL_RULE);
    }

    public static void greet() {
        System.out.println("\n" +
                "\n" +
                "               ('-.       .-') _   ('-.    .-')              .-')    \n" +
                "             _(  OO)     ( OO ) )_(  OO)  ( OO ).           ( OO ).  \n" +
                "  ,----.    (,------.,--./ ,--,'(,------.(_)---\\_)  ,-.-') (_)---\\_) \n" +
                " '  .-./-')  |  .---'|   \\ |  |\\ |  .---'/    _ |   |  |OO)/    _ |  \n" +
                " |  |_( O- ) |  |    |    \\|  | )|  |    \\  :` `.   |  |  \\\\  :` `.  \n" +
                " |  | .--, \\(|  '--. |  .     |/(|  '--.  '..`''.)  |  |(_/ '..`''.) \n" +
                "(|  | '. (_/ |  .--' |  |\\    |  |  .--' .-._)   \\ ,|  |_.'.-._)   \\ \n" +
                " |  '--'  |  |  `---.|  | \\   |  |  `---.\\       /(_|  |   \\       / \n" +
                "  `------'   `------'`--'  `--'  `------' `-----'   `--'    `-----'  \n" +
                "\n");

        System.out.println(HORIZONTAL_RULE);
        System.out.println("What can I do for you?\n");
        System.out.println(HORIZONTAL_RULE);
    }

    public static void goodbye() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public static void listTasks(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println((i + 1) + ". " + task.getListDescription());
        }
    }

    public static void onTaskMarked(String description) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(description);
    }

    public static void onTaskUnmarked(String description) {
        System.out.println("Nice! I've marked this task as not done:");
        System.out.println(description);
    }

    public static void onTaskAdded(String description, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(description);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public static void onTaskDelete(String description, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(description);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public static void helpAll() {
        System.out.println("Commands:");
        System.out.println("  list");
        System.out.print("  ");
        helpTodo();
        System.out.print("  ");
        helpDeadline();
        System.out.print("  ");
        helpEvent();
        System.out.print("  ");
        helpMark();
        System.out.print("  ");
        helpUnark();
        System.out.print("  ");
        helpDelete();
        System.out.print("  ");
        helpFind();
        System.out.println("  bye");
    }

    public static void helpTodo() {
        System.out.println("todo <task_description>");
    }

    public static void helpDeadline() {
        System.out.println("deadline <task_description> /by <date>");
    }

    public static void helpEvent() {
        System.out.println("event <task_description> /from <start_date> /to <end_date>");
    }

    public static void helpMark() {
        System.out.println("mark <index>");
    }

    public static void helpUnark() {
        System.out.println("unmark <index>");
    }

    public static void helpDelete() {
        System.out.println("delete <index>");
    }

    public static void helpFind() {
        System.out.println("find <keyword>");
    }
}
