import java.util.Set;
import java.util.Scanner;
import java.util.HashSet;

public class Duke {
    // Messages
    private static final String BOT_NAME = "Duke";
    private static final String INITIAL_MSG = String.format("    Hello! I'm %s\n   What can I do for you?", BOT_NAME); 
    private static final String BYE_MSG = "     Bye. Hope to see you again soon!";
    private static final String MARK_MSG = "     Nice! I've marked this task as done";
    private static final String UNMARK_MSG = "     OK, I've marked this task as not done yet:";
    // Constants 
    private static final int MAX_ITEMS = 100;
    private static String[] itemList = new String[MAX_ITEMS];
    private static Set<String> markedItems = new HashSet<String>(MAX_ITEMS);
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
        } else if (command.equals("list")) {
            printItems();
        } else if (command.equals("mark")) {
            // String item = line.substring(4); // if want to mark by name
            markItem(Integer.parseInt(lineParts[1]));
        } else if (command.equals("unmark")) {
            // String item = line.substring(6); // if want to unmark by name
            unmarkItem(Integer.parseInt(lineParts[1]));
        } else { // add item
            addItem(line);
        }
    }

    private static void markItem(int itemNo) {
        String item = itemList[itemNo - 1];
        markedItems.add(item);
        printResponse("    " + MARK_MSG + "\n" + "        [X] " + item);
    }

    private static void unmarkItem(int itemNo) {
        String item = itemList[itemNo - 1];
        markedItems.remove(item);
        printResponse("    " + UNMARK_MSG + "\n" + "        [ ] " + item);
    }

    private static void addItem(String item) {
        itemList[numItems++] = item;
        printResponse("    added: " + item);
    }

    private static void printItems() {
        String response = "";
        for (int i = 0; i < numItems; i++) {
            char mark;
            if (markedItems.contains(itemList[i])) {
                mark = 'X';
            } else {
                mark = ' ';
            }
            response += String.format("   %d.[%c] %s\n", i+1, mark, itemList[i]);
        }
        printResponse(response);
    }

    private static void printResponse(String response) {
        System.out.println("    ____________________________________________________________");
        System.out.println(String.format("%s", response));
        System.out.println("    ____________________________________________________________");
    }
}
