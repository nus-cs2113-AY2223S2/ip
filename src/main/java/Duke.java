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
        String[] message = inputMessage.split(" ");
        if (message.length == 1 && message[0].equalsIgnoreCase("bye")) {
            isCompleted = true;
            return;
        }

        if (message.length == 1 && message[0].equalsIgnoreCase("list")) {
            displayList();
            return;
        }

        if (message.length == 2 && message[0].equalsIgnoreCase("mark")) {
            markItem(Integer.parseInt(message[1]));
            return;
        }
        if (message.length == 2 && message[0].equalsIgnoreCase("unmark")) {
            unmarkItem(Integer.parseInt(message[1]));
            return;
        }

        addItem(inputMessage);
    }

    public static boolean isValidTaskNumber(int taskNumber) {
        return taskNumber > userList.size() ? false : true;
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
            String item = userList.get(i).toString();
            String outputMessage = String.format("%d.%s", i + 1, item);
            printMessage(outputMessage);
        }
        separator();
    }

    public static void markItem(int taskNumber) {
        separator();
        boolean isValidTask = isValidTaskNumber(taskNumber);
        if (!isValidTask) {
            String errorMessage = String.format("List only has %d items!", userList.size());
            printMessage(errorMessage);
        } else {
            userList.get(taskNumber - 1).setAsDone();
            String outputMessage = String.format("Nice! I've marked task %d as done:", taskNumber);
            printMessage(outputMessage);
            printMessage(userList.get(taskNumber - 1).toString());
        }
        separator();
    }

    public static void unmarkItem(int taskNumber) {
        separator();
        boolean isValidTask = isValidTaskNumber(taskNumber);
        if (!isValidTask) {
            String errorMessage = String.format("List only has %d items!", userList.size());
            printMessage(errorMessage);
        } else {
            userList.get(taskNumber - 1).setAsNotDone();
            String outputMessage = String.format("OK, I've marked task %d as not done yet:", taskNumber);
            printMessage(outputMessage);
            printMessage(userList.get(taskNumber - 1).toString());
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
