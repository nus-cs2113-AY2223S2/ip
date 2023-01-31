import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    //TODO make methods for the different operations
    protected static Task[] list = new Task[100];
    public static final String OPENING_MSG = "Hello! I'm Dukebot\n\tWhat can I do for you?";
    public static final String CLOSING_MSG = "Goodbye! Hope to see you again soon ^^!";
    public static final String HORIZONTAL = "---------------------------------";
    public static final String MARK_MSG = "Nice! I've marked this task as done:";
    public static final String UNMARK_MSG = "Oki! I've marked this task as not done yet:";

    public static void main(String[] args) {

        System.out.println(HORIZONTAL + "\n\t" + OPENING_MSG + "\n" + HORIZONTAL);

        Scanner in = new Scanner(System.in);
        String line = in.nextLine().toLowerCase();

        int listNum;

        int item = 0;

        while (!line.equals("bye")) {
            if (!(line.equals("list") || line.startsWith("mark") || line.startsWith("unmark"))) {
                list[item] = new Task(line);
                System.out.println(HORIZONTAL + "\n\tadded: " + list[item].description + "\n" + HORIZONTAL);
                item++;
            }

            line = in.nextLine().toLowerCase();

            // display list
            if (line.equals("list")) {
                System.out.println(HORIZONTAL + "\n\tHere are the tasks in your list:");
                for (int i = 0; i < item; i++) {
                    System.out.println("\n\t" + (i + 1) + ". [" + list[i].getStatusIcon() + "] " + list[i].description + "\n");
                }
                System.out.println(HORIZONTAL);
            }

            // Mark item as done: constraint - user input begins with "mark"
            if (line.startsWith("mark")) {

                StatusToggle(line, item, MARK_MSG, true);

            }

            // Unmark item: constraint - user input begins with "mark"
            if (line.startsWith("unmark")) {

                StatusToggle(line, item, UNMARK_MSG, false);

            }

        }

        System.out.println(HORIZONTAL + "\n\t" + CLOSING_MSG + "\n" + HORIZONTAL);

    }

    public static void StatusToggle(String line, int item, String msg, boolean status) {
        String lineAr[] = new String[2];

        lineAr = line.split(" ");
        int listNum = Integer.parseInt(lineAr[1]) - 1;

        if (listNum >= 0 && listNum < item) {

            // Check if it is already done/not done in list
            if (list[listNum].isDone != status) {
                list[listNum].isDone = status;

                System.out.println(HORIZONTAL + "\n\t" + msg);
                System.out.println("\n\t\t" + "[" + list[listNum].getStatusIcon() + "] " + list[listNum].description + "\n");
                System.out.println(HORIZONTAL);
            } else {
                System.out.println("No change, task was already as is");
            }

        } else {
            System.out.println("Item number " + (listNum + 1) + " does not exist yet");
        }

    }
}
