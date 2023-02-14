import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    protected static Task[] list = new Task[100];
    public static final String OPENING_MSG = "Hello! I'm Dukebot\n\tWhat can I do for you?";
    public static final String CLOSING_MSG = "Goodbye! Hope to see you again soon ^^!";
    public static final String HORIZONTAL = "---------------------------------";
    public static final String MARK_MSG = "Nice! I've marked this task as done:";
    public static final String UNMARK_MSG = "Oki! I've marked this task as not done yet:";

    public static void main(String[] args) {

        System.out.println(HORIZONTAL + "\n\t" + OPENING_MSG + "\n" + HORIZONTAL);

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        int numToMark;
        int item = 0;

        while (!line.equals("bye")) {
            if (!(line.startsWith("event") || line.startsWith("deadline") || line.startsWith("todo") || line.equals("list") || line.startsWith("mark") || line.startsWith("unmark"))) {
                System.out.println(HORIZONTAL);
                System.out.println("\tSorry, I don't know what that means!");
                System.out.println(HORIZONTAL);
            } else {
                // Add event
                if (line.toLowerCase().startsWith("event")) {
                    item = addEvent(line, item);
                }
                // Add deadline
                if (line.toLowerCase().startsWith("deadline")) {
                    item = addDeadline(line, item);
                }
                // Add todo
                if (line.toLowerCase().startsWith("todo")) {
                    item = addToDo(line, item);
                }

                // Display list
                if (line.equalsIgnoreCase("list")) {
                    displayList(item);
                }

                // Mark item as done: constraint - user input begins with "mark"
                if (line.toLowerCase().startsWith("mark")) {
                    toggleDoneStatus(line, item, MARK_MSG, true);
                }
                // Unmark item: constraint - user input begins with "mark"
                if (line.toLowerCase().startsWith("unmark")) {
                    toggleDoneStatus(line, item, UNMARK_MSG, false);
                }
            }
            // Read next line
            line = in.nextLine();
        }
        System.out.println(HORIZONTAL + "\n\t" + CLOSING_MSG + "\n" + HORIZONTAL);

    }

    public static void displayTaskAddedMessage(Task item) {
        System.out.println(HORIZONTAL + "\n\tGot it! Added this task: " + "\n\t\t" + item.getDescription());
    }

    public static void displayNumItemsInList(int item) {
        if (item > 0) {
            System.out.println("\tNow you have " + (item + 1) + " tasks in the list");
        } else {
            System.out.println("\tNow you have " + (item + 1) + " task in the list");
        }
    }

    public static int addEvent(String line, int item) {
        try {
            int toIndex = line.indexOf("/to");
            String toDate = line.substring(toIndex + 3);
            int fromIndex = line.indexOf("/from");
            String fromDate = line.substring(fromIndex + 5, toIndex);
            int descriptionIndex = line.indexOf("event");
            String description = line.substring(descriptionIndex + 6, fromIndex);

            list[item] = new Event(description, fromDate, toDate);

            displayTaskAddedMessage(list[item]);
            displayNumItemsInList(item);
            System.out.println(HORIZONTAL);

            item++;
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(HORIZONTAL);
            System.out.println("\tCannot add, the description of an event cannot be empty!");
            System.out.println(HORIZONTAL);

        } finally {
            return item;

        }
    }

    public static int addDeadline(String line, int item) {
        try {
            int byIndex = line.indexOf("/by");
            String byDate = line.substring(byIndex + 3);
            int descriptionIndex = line.indexOf("deadline");
            String description = line.substring(descriptionIndex + 9, byIndex);

            list[item] = new Deadline(description, byDate);

            displayTaskAddedMessage(list[item]);
            displayNumItemsInList(item);
            System.out.println(HORIZONTAL);

            item++;
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(HORIZONTAL);
            System.out.println("\tCannot add, the description of a deadline cannot be empty!");
            System.out.println(HORIZONTAL);

        } finally {
            return item;

        }
    }

    public static int addToDo(String line, int item) {
        try {
            int descriptionIndex = line.indexOf("todo");
            String description = line.substring(descriptionIndex + 5);
            list[item] = new Todo(description);

            displayTaskAddedMessage(list[item]);
            displayNumItemsInList(item);
            System.out.println(HORIZONTAL);

            item++;

        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(HORIZONTAL);
            System.out.println("\tCannot add, the description of a todo cannot be empty!");
            System.out.println(HORIZONTAL);

        } finally {
            return item;

        }

    }

    public static void displayList(int item) {
        System.out.println(HORIZONTAL + "\n\tHere are the tasks in your list:");
        for (int i = 0; i < item; i++) {
            System.out.println("\n\t" + (i + 1) + ". " + list[i].getDescription() + "\n");
        }
        System.out.println(HORIZONTAL);
    }

    public static void toggleDoneStatus(String line, int item, String msg, boolean status) {
        try {
            String inputMessageArray[] = new String[2];
            inputMessageArray = line.split(" ");
            int numToMark = Integer.parseInt(inputMessageArray[1]) - 1;

            // Check if list item number exists in list
            if (numToMark >= 0 && numToMark < item) {
                // Check if it is already done/not done in list
                if (list[numToMark].isDone != status) {
                    // Update IsDone status
                    list[numToMark].isDone = status;

                    System.out.println(HORIZONTAL + "\n\t" + msg);
                    System.out.println("\n\t\t" + "[" + list[numToMark].getStatusIcon() + "] " + list[numToMark].description + "\n");
                    System.out.println(HORIZONTAL);
                } else {
                    System.out.println("No change, task was already as is");
                }
            } else {
                System.out.println("Item number " + (numToMark + 1) + " does not exist yet");
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(HORIZONTAL);
            System.out.println("\tI cannot change the status if I don't know the list number!");
            System.out.println(HORIZONTAL);

        }
    }
}
