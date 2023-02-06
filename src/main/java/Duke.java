import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {

    static int BOX_WIDTH = 100;

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
        String separatorLine = "-".repeat(BOX_WIDTH);
        System.out.println(separatorLine);
    }

    public static void printMessage(String message) {
        String outputMessage = String.format("| %-97s|", message);
        System.out.println(outputMessage);
    }

    public static void checkInput(String inputMessage) {
        String cleanInput = inputMessage.trim();
        String[] message = cleanInput.split(" ");
        if (message[0].equalsIgnoreCase("bye")) {
            isCompleted = true;
        } else if (message[0].equalsIgnoreCase("list")) {
            displayList();
        } else if (message[0].equalsIgnoreCase("mark")) {
            markItem(message);
        } else if (message[0].equalsIgnoreCase("unmark")) {
            unmarkItem(message);
        } else if (message[0].equalsIgnoreCase("todo")) {
            addTodo(cleanInput);
        } else if (message[0].equalsIgnoreCase("deadline")) {
            addDeadline(message);
        } else if (message[0].equalsIgnoreCase("event")) {
            addEvent(message);
        } else {
            addTask(cleanInput);
        }
    }

    public static void markItem(String[] message) {
        int taskNumber = 0;

        separator();
        try {
            taskNumber = getTaskNumber(message);
            userList.get(taskNumber - 1).setAsNotDone();
            String outputMessage = String.format("Nice! I've marked task %d as done:", taskNumber);
            printMessage(outputMessage);
            printMessage(userList.get(taskNumber - 1).toString());
        } catch (DukeWrongArgsException error) {
            String errorMessage = String.format("Wrong number of arguments. Expected 2, received %d",
                    message.length);
            printMessage(errorMessage);
        } catch (NumberFormatException error) {
            String errorMessage = "Expected a valid number for second argument.";
            String errorMessageEcho = String.format("You entered %s, which is invalid!", message[1]);
            printMessage(errorMessage);
            printMessage(errorMessageEcho);
        } catch (IndexOutOfBoundsException error) {
            String errorMessage = "Out of bounds value provided.";
            String errorMessageEcho = String.format("List only has %d items, you entered %d!",
                    userList.size(), taskNumber);
            printMessage(errorMessage);
            printMessage(errorMessageEcho);
        } finally {
            separator();
        }
    }

    public static void unmarkItem(String[] message) {
        int taskNumber = 0;

        separator();
        try {
            taskNumber = getTaskNumber(message);
            userList.get(taskNumber - 1).setAsNotDone();
            String outputMessage = String.format("OK, I've marked task %d as not done yet:", taskNumber);
            printMessage(outputMessage);
            printMessage(userList.get(taskNumber - 1).toString());
        } catch (DukeWrongArgsException error) {
            String errorMessage = String.format("Wrong number of arguments. Expected 2, received %d",
                    message.length);
            printMessage(errorMessage);
        } catch (NumberFormatException error) {
            String errorMessage = "Expected a valid number for second argument.";
            String errorMessageEcho = String.format("You entered %s, which is invalid!", message[1]);
            printMessage(errorMessage);
            printMessage(errorMessageEcho);
        } catch (IndexOutOfBoundsException error) {
            String errorMessage = "Out of bounds value provided.";
            String errorMessageEcho = String.format("List only has %d items, you entered %d!",
                    userList.size(), taskNumber);
            printMessage(errorMessage);
            printMessage(errorMessageEcho);
        } finally {
            separator();
        }
    }

    public static int getTaskNumber(String[] message) throws DukeWrongArgsException, NumberFormatException {
        // Check for correct number of arguments
        if (message.length != 2) {
            throw new DukeWrongArgsException();
        }

        // Check that second argument provided is a valid number
        try {
            return Integer.parseInt(message[1]);
        } catch (NumberFormatException error) {
            throw new NumberFormatException();
        }
    }

    public static String parseTodo(String message) throws StringIndexOutOfBoundsException {
        int secondArgStartIndex = 5;
        if (message.length() < secondArgStartIndex) {
            throw new StringIndexOutOfBoundsException();
        } else {
            return message.substring(secondArgStartIndex);
        }
    }

    public static Deadline parseDeadline(String[] message) throws DukeWrongArgsException {
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

        // Checks if correct argument is provided
        if (endDateStartIndex == 0) {
            throw new DukeWrongArgsException();
        }

        String[] descriptionArray = Arrays.copyOfRange(message, descriptionStartIndex, descriptionEndIndex);
        String[] endDateArray = Arrays.copyOfRange(message, endDateStartIndex, endDateEndIndex);

        String[] deadlineArray = new String[2];
        deadlineArray[0] = String.join(" ", descriptionArray);
        deadlineArray[1] = String.join(" ", endDateArray);

        return new Deadline(deadlineArray[0], deadlineArray[1]);
    }

    public static Event parseEvent(String[] message) throws DukeWrongArgsException {
        int descriptionStartIndex = 1;
        int descriptionEndIndex = 0;
        int startDateStartIndex = 0;
        int startDateEndIndex = 0;
        int endDateStartIndex = 0;
        int endDateEndIndex = message.length;

        for (int i = 1; i < message.length; i++) {
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

        if (startDateStartIndex == 0 || startDateEndIndex == 0) {
            throw new DukeWrongArgsException();
        }

        String[] descriptionArray = Arrays.copyOfRange(message, descriptionStartIndex, descriptionEndIndex);
        String[] startDateArray = Arrays.copyOfRange(message, startDateStartIndex, startDateEndIndex);
        String[] endDateArray = Arrays.copyOfRange(message, endDateStartIndex, endDateEndIndex);

        String[] eventArray = new String[3];
        eventArray[0] = String.join(" ", descriptionArray);
        eventArray[1] = String.join(" ", startDateArray);
        eventArray[2] = String.join(" ", endDateArray);

        return new Event(eventArray[0], eventArray[1], eventArray[2]);
    }

    public static void addTask(String description) {
        separator();
        Task t = new Task(description);
        userList.add(t);
        String outputMessage = String.format("added: %s", description);
        printMessage(outputMessage);
        separator();
    }

    public static void addTodo(String cleanInput) {
        separator();

        // Check if second argument was provided
        try {
            String todoDescription = parseTodo(cleanInput);
            Todo todo = new Todo(todoDescription);
            userList.add(todo);
            printMessage("Got it. I've added this todo:");
            printMessage(String.format(" %s", todo));
            printMessage(String.format("Now you have %d tasks in the list.", userList.size()));
        } catch (StringIndexOutOfBoundsException error) {
            String errorMessage = "Expected 2 arguments, only 1 provided.";
            printMessage(errorMessage);
        } finally {
            separator();
        }
    }

    public static void addDeadline(String[] message) {
        separator();

        // Check if task and deadline given
        try {
            Deadline deadline = parseDeadline(message);
            userList.add(deadline);
            printMessage("Got it. I've added this deadline:");
            printMessage(String.format(" %s", deadline));
            printMessage(String.format("Now you have %d tasks in the list.", userList.size()));
        } catch (DukeWrongArgsException error) {
            String errorMessage = "Command to enter new deadline entered wrongly.";
            String errorMessageExample = "Example command: \"deadline <task> /by <endDate>\"";
            printMessage(errorMessage);
            printMessage(errorMessageExample);
        } finally {
            separator();
        }

    }

    public static void addEvent(String[] message) {
        separator();

        // Check if task, start and end date given
        try {
            Event event = parseEvent(message);
            userList.add(event);
            printMessage("Got it. I've added this event:");
            printMessage(String.format(" %s", event));
            printMessage(String.format("Now you have %d tasks in the list.", userList.size()));
        } catch (DukeWrongArgsException error) {
            String errorMessage = "Command to enter new event entered wrongly.";
            String errorMessageExample =
                    "Example command: \"event <task> /from <startDate> /to " + "<endDate>\"";
            printMessage(errorMessage);
            printMessage(errorMessageExample);
        } finally {
            separator();
        }
    }

    public static void displayList() {
        separator();
        int numItems = userList.size();
        if (numItems == 0) {
            printMessage("List is empty!");
            separator();
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
