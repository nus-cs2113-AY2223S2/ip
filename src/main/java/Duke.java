import java.util.ArrayList;
import java.util.Arrays;
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

        if (message.length > 1 && message[0].equalsIgnoreCase("todo")) {
            String todoDescription = inputMessage.substring(5);
            addTodo(todoDescription);
            return;
        }

        if (message.length > 1 && message[0].equalsIgnoreCase("deadline")) {
            String[] deadlineInfo = parseDeadline(message);
            addDeadline(deadlineInfo[0], deadlineInfo[1]);
            return;
        }

        if (message.length > 1 && message[0].equalsIgnoreCase("event")) {
            String[] eventInfo = parseEvent(message);
            addEvent(eventInfo[0], eventInfo[1], eventInfo[2]);
            return;
        }

        addTask(inputMessage);
    }

    public static boolean isValidTaskNumber(int taskNumber) {
        return taskNumber <= userList.size();
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

    public static String[] parseDeadline(String[] message) {
        int descriptionStartIndex = 1;
        int descriptionEndIndex = 0;
        int endDateStartIndex = 0;
        int endDateEndIndex = message.length;

        for (int i = 2; i < message.length; i++) {
            if (message[i].equalsIgnoreCase("/by")) {
                descriptionEndIndex = i;
                endDateStartIndex = i + 1;
                break;
            }
        }

        String[] descriptionArray = Arrays.copyOfRange(message, descriptionStartIndex, descriptionEndIndex);
        String[] endDateArray = Arrays.copyOfRange(message, endDateStartIndex, endDateEndIndex);

        String[] deadlineArray = new String[2];
        deadlineArray[0] = String.join(" ", descriptionArray);
        deadlineArray[1] = String.join(" ", endDateArray);
        return deadlineArray;
    }

    public static String[] parseEvent(String[] message) {
        int descriptionStartIndex = 1;
        int descriptionEndIndex = 0;
        int startDateStartIndex = 0;
        int startDateEndIndex = 0;
        int endDateStartIndex = 0;
        int endDateEndIndex = message.length;

        for (int i = 2; i < message.length; i++) {
            if (message[i].equalsIgnoreCase("/from")) {
                descriptionEndIndex = i;
                startDateStartIndex = i + 1;

            }
            if (message[i].equalsIgnoreCase("/to")) {
                startDateEndIndex = i;
                endDateStartIndex = i + 1;
                break;
            }
        }

        String[] descriptionArray = Arrays.copyOfRange(message, descriptionStartIndex, descriptionEndIndex);
        String[] startDateArray = Arrays.copyOfRange(message, startDateStartIndex, startDateEndIndex);
        String[] endDateArray = Arrays.copyOfRange(message, endDateStartIndex, endDateEndIndex);

        String[] eventArray = new String[3];
        eventArray[0] = String.join(" ", descriptionArray);
        eventArray[1] = String.join(" ", startDateArray);
        eventArray[2] = String.join(" ", endDateArray);
        return eventArray;
    }

    public static void addTask(String description) {
        separator();
        Task t = new Task(description);
        userList.add(t);
        String outputMessage = String.format("added: %s", description);
        printMessage(outputMessage);
        separator();
    }

    public static void addTodo(String description) {
        separator();
        Todo todo = new Todo(description);
        userList.add(todo);
        printMessage("Got it. I've added this todo:");
        printMessage(String.format(" %s", todo));
        printMessage(String.format("Now you have %d tasks in the list.", userList.size()));
        separator();
    }

    public static void addDeadline(String description, String endDate) {
        separator();
        Deadline deadline = new Deadline(description, endDate);
        userList.add(deadline);
        printMessage("Got it. I've added this deadline:");
        printMessage(String.format(" %s", deadline));
        printMessage(String.format("Now you have %d tasks in the list.", userList.size()));
        separator();
    }

    public static void addEvent(String description, String startDate, String endDate) {
        separator();
        Event event = new Event(description, startDate, endDate);
        userList.add(event);
        printMessage("Got it. I've added this event:");
        printMessage(String.format(" %s", event));
        printMessage(String.format("Now you have %d tasks in the list.", userList.size()));
        separator();
    }

    public static void displayList() {
        separator();
        int numItems = userList.size();
        if (numItems == 0) {
            printMessage("List is empty!");
            return;
        }

        printMessage("Here are the tasks in your list:");
        for (int i = 0; i < numItems; i++) {
            String item = userList.get(i).toString();
            String outputMessage = String.format("%d.%s", i + 1, item);
            printMessage(outputMessage);
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
