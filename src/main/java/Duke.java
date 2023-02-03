import java.util.Set;
import java.util.Scanner;
import java.util.HashSet;

public class Duke {
    // Messages
    private static final String BOT_NAME = "Duke";
    private static final String INITIAL_MSG = String.format("    Hello! I'm %s\n    What can I do for you?", BOT_NAME); 
    private static final String BYE_MSG = "     Bye. Hope to see you again soon!";
    private static final String MARK_MSG = "     Nice! I've marked this task as done";
    private static final String UNMARK_MSG = "     OK, I've marked this task as not done yet:";
    private static final String INVALID_MSG = "    Invalid Message please try again";
    private static final String SHOW_ITEMS_MSG = "    Here are the tasks in your list:";
    private static final String ADDED_MSG = "    Got it. I've added this task:\n      %s\n    Now you have %d tasks in your list.";
    // Constants 
    private static final int MAX_ITEMS = 100;
    private static Todo[] itemList = new Todo[MAX_ITEMS];
    // private static Set<String> markedItems = new HashSet<String>(MAX_ITEMS);
    private static int numItems = 0;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printResponse(INITIAL_MSG);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            handleInput(line);
        }
        scanner.close();
    }

    private static void handleInput(String line) {
        String[] lineParts = line.split(" ");
        String command = lineParts[0];
        if (command.equals("bye")) {
            printResponse(BYE_MSG);
        } else if (command.equals("todo")) {
            line = line.substring(command.length() + 1);
            addItem(new Todo(line));
        } else if (command.equals("deadline")) {
            createDeadline(lineParts);
        } else if (command.equals("event")) {
            createEvent(lineParts);
        } else if (command.equals("list")) {
            printItems();
        } else if (command.equals("mark")) {
            // String item = line.substring(4); // if want to mark by name
            markItem(Integer.parseInt(lineParts[1]));
        } else if (command.equals("unmark")) {
            // String item = line.substring(6); // if want to unmark by name
            unmarkItem(Integer.parseInt(lineParts[1]));
        } else { // add item
            printResponse(INVALID_MSG);
        }
    }

    private static void createDeadline(String[] lineParts) {
        String itemDescription = "";
        String itemDeadline = "";
        int i = 1;
        for (; i < lineParts.length; i++) {
            if (lineParts[i].equals("/by")) {
                i++;
                break;
            } else {
                itemDescription += lineParts[i] + ' ';
            }
        }
        for (; i < lineParts.length; i++) {
            itemDeadline += lineParts[i] + ' ';
        }
        addItem(new Deadline(itemDescription, itemDeadline));
    }

    private static void createEvent(String[] lineParts) {
        String itemDescription = "";
        String itemFrom = "";
        String itemTo = "";
        int i = 1;
        for (; i < lineParts.length; i++) {
            if (lineParts[i].equals("/from")) {
                i++;
                break;
            } else {
                itemDescription += lineParts[i] + ' ';
            }
        }
        for (; i < lineParts.length; i++) {
            if (lineParts[i].equals("/to")) {
                i++;
                break;
            } else {
                itemFrom += lineParts[i] + ' ';
            }
        }
        for (; i < lineParts.length; i++) {
            itemTo += lineParts[i] + ' ';
        }
        addItem(new Event(itemDescription, itemFrom, itemTo));
    }

    private static void markItem(int itemNo) {
        Todo item = itemList[itemNo - 1];
        item.setDone(true);
        printResponse("    " + MARK_MSG + "\n" + "    " + item.toString());
    }

    private static void unmarkItem(int itemNo) {
        Todo item = itemList[itemNo - 1];
        item.setDone(false);
        printResponse("    " + UNMARK_MSG + "\n" + "    " + item.toString());
    }

    private static void addItem(Todo item) {
        itemList[numItems++] = item;
        printResponse(String.format(ADDED_MSG, item.toString(), numItems));
    }

    private static void printItems() {
        String response = SHOW_ITEMS_MSG + "\n";
        for (int i = 0; i < numItems; i++) {
            Todo item = itemList[i];
            response += String.format("    %d.%s\n", i+1, item.toString());
        }
        printResponse(response);
    }

    private static void printResponse(String response) {
        System.out.println("    ____________________________________________________________");
        System.out.println(String.format("%s", response));
        System.out.println("    ____________________________________________________________");
    }
}
