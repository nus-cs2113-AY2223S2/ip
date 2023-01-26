import java.util.Scanner;

public class Duke {
    private static final String BOT_NAME = "Duke";
    private static final String INITIAL_MSG = String.format("    Hello! I'm %s\n   What can I do for you?", BOT_NAME); 
    private static final String BYE_MSG = "     Bye. Hope to see you again soon!";
    private static String[] itemList = new String[100];
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
        if (line.equals("bye")) {
            printResponse(BYE_MSG);
        } else if (line.equals("list")) {
            printItems();
        } else { // add item
            addItem(line);
        }
    }

    private static void addItem(String item) {
        itemList[numItems++] = item;
        printResponse("    added: " + item);
    }

    private static void printItems() {
        String response = "";
        for (int i = 0; i < numItems; i++) {
            response += String.format("   %d. %s\n", i+1, itemList[i]);
        }
        printResponse(response);
    }

    private static void printResponse(String response) {
        System.out.println("    ____________________________________________________________");
        System.out.println(String.format("    %s", response));
        System.out.println("    ____________________________________________________________");
    }
}
