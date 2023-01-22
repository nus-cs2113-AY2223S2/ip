import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static boolean isCompleted = false;
    private static List<String> userList = new ArrayList<String>();

    public static void welcomeMessage() {
        separator();
        formatMessage("Hello! I'm Duke");
        formatMessage("What can I do for you?");
        separator();
    }

    public static void endingMessage() {
        formatMessage("Bye. Hope to see you again soon!");
    }

    public static void separator() {
        String separatorLine = "-".repeat(60);
        System.out.println(separatorLine);
    }

    public static void formatMessage(String message) {
        String outputMessage = String.format("| %-57s|", message);
        System.out.println(outputMessage);
    }

    public static void checkInput(String inputMessage) {
        if (inputMessage.equalsIgnoreCase("bye")) {
            isCompleted = true;
            separator();
            endingMessage();
            separator();
        } else if (inputMessage.equalsIgnoreCase("list")) {
            separator();
            displayList();
            separator();
        } else {
            separator();
            addItem(inputMessage);
            separator();
        }
    }

    public static void addItem(String item) {
        userList.add(item);
        String outputMessage = String.format("added: %s", item);
        formatMessage(outputMessage);
    }

    public static void displayList() {
        int numItems = userList.size();
        for (int i = 0; i < numItems; i++) {
            String outputMessage = String.format("%d. %s", i+1, userList.get(i));
            formatMessage(outputMessage);
        }
    }

    public static void main(String[] args) {
        welcomeMessage();
        String line;
        Scanner in = new Scanner(System.in);

        do {
            line = in.nextLine();
            checkInput(line);
        } while (!isCompleted);
    }
}
