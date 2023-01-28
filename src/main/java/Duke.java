import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static boolean isCompleted = false;
    private static final List<Task> userList = new ArrayList<>();

    public static void welcomeMessage() {
        separator();
        printMessage("Hello! I'm Duke");
        printMessage("What can I do for you?");
        separator();
    }

    public static void endingMessage() {
        separator();
        printMessage("Bye. Hope to see you again soon!");
        separator();
    }

    public static void separator() {
        String separatorLine = "-".repeat(60);
        System.out.println(separatorLine);
    }

    public static void printMessage(String message) {
        String outputMessage = String.format("| %-57s|", message);
        System.out.println(outputMessage);
    }

    public static void checkInput(String inputMessage) {
        if (inputMessage.equalsIgnoreCase("bye")) {
            isCompleted = true;
            return;
        }
        if (inputMessage.equalsIgnoreCase("list")) {
            displayList();
            return;
        }
        if (inputMessage.startsWith("mark")) {
            markItem(inputMessage);
            return;
        }
        if (inputMessage.startsWith("unmark")) {
            unmarkItem(inputMessage);
            return;
        }

        addItem(inputMessage);
    }
    
    public static int checkValidTask(String inputMessage) {
        String[] message = inputMessage.split(" ");
        int itemNumber = Integer.parseInt(message[1]);
        return itemNumber > userList.size() ? -1 : itemNumber;
    }

    public static void addItem(String item) {
        separator();
        Task t = new Task(item);
        userList.add(t);
        String outputMessage = String.format("added: %s", item);
        printMessage(outputMessage);
        separator();
    }

    public static void displayList() {
        separator();
        int numItems = userList.size();
        if (numItems == 0) {
            printMessage("List is empty!");
            return;
        }

        for (int i = 0; i < numItems; i++) {
            String item = displayItem(i + 1);
            String outputMessage = String.format("%d.%s", i + 1, item);
            printMessage(outputMessage);
        }
        separator();
    }

    public static String displayItem(int index) {
        String itemStatus = userList.get(index - 1).getStatusIcon();
        String itemDescription = userList.get(index - 1).getDescription();
        return String.format("[%s] %s", itemStatus, itemDescription);
    }

    public static void markItem(String inputMessage) {
        separator();
        int itemNumber = checkValidTask(inputMessage);
        if (itemNumber == -1) {
            String errorMessage = String.format("List only has %d items!", userList.size());
            printMessage(errorMessage);
        } else {
            userList.get(itemNumber - 1).setAsDone();
            String outputMessage = String.format("Nice! I've marked task %d as done:", itemNumber);
            printMessage(outputMessage);
            printMessage(displayItem(itemNumber));
        }
        separator();
    }

    public static void unmarkItem(String inputMessage) {
        separator();
        int itemNumber = checkValidTask(inputMessage);
        if (itemNumber == -1) {
            String errorMessage = String.format("List only has %d items!", userList.size());
            printMessage(errorMessage);
        } else {
            userList.get(itemNumber - 1).setAsNotDone();
            String outputMessage = String.format("OK, I've marked task %d as not done yet:", itemNumber);
            printMessage(outputMessage);
            printMessage(displayItem(itemNumber));
        }
        separator();
    }

    public static void main(String[] args) {
        welcomeMessage();

        String line;
        Scanner in = new Scanner(System.in);

        do {
            line = in.nextLine();
            checkInput(line);
        } while (!isCompleted);
        
        endingMessage();
    }
}
