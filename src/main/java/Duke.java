import java.util.ArrayList;
import java.nio.file.Paths;
import java.util.Scanner;

import exceptions.MarkOutOfBounds;
import exceptions.UnmarkOutOfBounds;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Duke {
    /***
     * Ensures that a fixed line formatting is made.
     */
    public static final String LINE_FORMATTING =
            "____________________________________________________________\n";
    /***
     * The fixed number of spaces when processing commands involving "by" in
     * the input.
     */
    public static final int REMOVE_BY_NUM = 4;
    /***
     * The fixed number of spaces when processing commands involving "todo" in
     * the input.
     */
    public static final int REMOVE_TODO_NUM = 5;
    /***
     * The fixed number of spaces when processing commands involving "event" in
     * the input.
     */
    public static final int REMOVE_EVENT_NUM = 6;
    /***
     * The fixed number of spaces when processing commands involving "from" in
     * the input.
     */
    public static final int REMOVE_FROM_NUM = 6;
    /***
     * The fixed number of spaces when processing commands involving "to" in
     * the input.
     */
    public static final int REMOVE_TO_NUM = 4;
    /***
     * The fixed number of spaces when processing commands involving "unmark" in
     * the input.
     */
    public static final int REMOVE_UNMARK_NUM = 7;
    /***
     * The fixed number of spaces when processing commands involving "mark" in
     * the input.
     */
    public static final int REMOVE_MARK_NUM = 5;
    /***
     * The fixed number of spaces when processing commands involving "delete" in
     * the input.
     */
    public static final int REMOVE_DELETE_NUM = 7;

    /***
     * Keeps track of the current position of task in the list.
     */
    private static int taskNum = 0;
    /***
     * Resizeable array that stores the user inputs.
     */
    private static ArrayList<Task> storedValues = new ArrayList<>();
    /***
     * Home directory for creating directory and txt file.
     */
    public static final String HOME = System.getProperty("user.home");
    /***
     * Fixed directory where txt file will be saved.
     */
    public static final File FILEPATH = Paths.get(HOME, "IdeaProjects", "ip", "src",
            "main", "data", "duke-inputs.txt").toFile();

    /***
     * Main function greets the user and runs processInputs().
     */
    public static void main(String[] args) {
        try {
            fileAvailability();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!\n");
        }
    }

    /***
     * Checks if the file is already created in the user's environment. If not, fileAvailability will
     * create the directory or txt file, depending on what is missing.
     * Duke will only proceed when the txt file is detected.
     * @throws FileNotFoundException When the file is not found in the environment.
     */
    public static void fileAvailability() throws FileNotFoundException {
        File data = Storage.createFile();

        if (data.exists()) {
            showGreetings();
            acceptUserInputs(data);
            showGoodbye();
        }
    }

    /***
     * Reads in the user inputs and processes the commands.
     */
    private static void acceptUserInputs(File data) {
        try {
            readExistingData(data, storedValues);
        } catch (FileNotFoundException e) {
            System.out.println("File is not found!");
        }
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (!hasProcessedAllInputs(line, storedValues)) {
            line = in.nextLine();
        }
    }

    /***
     * Reads the existing data within the txt file before new user inputs are allowed. Ensures that the
     * list is the most updated since the last interaction with Duke.
     * @param data The txt file in the local environment.
     * @param storedValues List of inputs stored by the user.
     * @throws FileNotFoundException Thrown when file is not found in the environment.
     */
    private static void readExistingData(File data, ArrayList<Task> storedValues) throws FileNotFoundException {
        Scanner readData = new Scanner(data);
        while (readData.hasNext()) {
            // Previous data stored from calling duke
            String extractType[] = readData.nextLine().split(" | ", 5);
            String type = extractType[0];
            String marked = extractType[2];
            String task = extractType[4];

            switch (type) {
            case "T":
                readExistingToDo(storedValues, marked, task);
                break;
            case "D":
                readExistingDeadline(storedValues, extractType, marked);
                break;
            case "E":
                readExistingEvent(storedValues, extractType, marked);
                break;
            }
        }
    }

    /***
     * Reads the existing events within the txt file, processes it and displays it when the command "list"
     * is called by the user.
     * @param storedValues List of inputs stored by the user.
     * @param extractType Description of task with duration.
     * @param marked Status of marked in txt file.
     */
    private static void readExistingEvent(ArrayList<Task> storedValues, String[] extractType, String marked) {
        int firstSlash = extractType[4].indexOf("|");
        String eventName = extractType[4].substring(0,firstSlash-1);
        String eventDuration = extractType[4].substring(firstSlash+2);
        int secondSlash = eventDuration.indexOf("|");
        String eventFrom = eventDuration.substring(0,secondSlash-1);
        String eventTo = eventDuration.substring(secondSlash+2);
        Event addInputE = new Event(eventName, eventFrom, eventTo);
        storedValues.add(taskNum,addInputE);
        if (marked.equals("1")) {
            storedValues.get(taskNum).markAsDone();
        }
        taskNum += 1;
    }

    /***
     * Reads the existing deadlines within the txt file, processes it and displays it when the command "list"
     * is called by the user.
     * @param storedValues List of inputs stored by the user.
     * @param extractType Description of task with duration.
     * @param marked Status of marked in txt file.
     */
    private static void readExistingDeadline(ArrayList<Task> storedValues, String[] extractType, String marked) {
        int slash = extractType[4].indexOf("|");
        String deadlineTask = extractType[4].substring(0, slash-1);
        String deadlineBy = extractType[4].substring(slash+2);
        Deadline addInputD = new Deadline(deadlineTask, deadlineBy);
        storedValues.add(taskNum,addInputD);
        if (marked.equals("1")) {
            storedValues.get(taskNum).markAsDone();
        }
        taskNum += 1;
    }
    /***
     * Reads the existing todos within the txt file, processes it and displays it when the command "list"
     * is called by the user.
     * @param storedValues List of inputs stored by the user.
     * @param marked Status of marked in txt file.
     * @param task Description of task.
     */
    private static void readExistingToDo(ArrayList<Task> storedValues, String marked, String task) {
        Todo addInputT = new Todo(task);
        storedValues.add(taskNum,addInputT);
        if (marked.equals("1")) {
            storedValues.get(taskNum).markAsDone();
        }
        taskNum += 1;
    }

    /***
     * Detects the user input and matches the command with the desired output.
     * @param line User input to be processed.
     * @param storedValues List of inputs stored by the user.
     * @return False if bye command is not called.
     */
    private static boolean hasProcessedAllInputs(String line, ArrayList<Task> storedValues) {

        String splitInputs[] = line.split(" ", 2);
        String command = splitInputs[0];
        switch (command) {
        case "bye":
            return true;
        case "list":
            printList(storedValues);
            break;
        case "mark":
            try {
                markItem(storedValues, line, taskNum);
            } catch (MarkOutOfBounds e) {
                System.out.println("Item to mark is not in list!");
            } catch (IOException e) {
                System.out.println("File is not found!");
            }
            break;
        case "unmark":
            try {
                unmarkItem(storedValues, line);
            } catch (UnmarkOutOfBounds e) {
                System.out.println("Item to unmark is not in list!");
            } catch (IOException e) {
                System.out.println("File is not found!");
            }
            break;
        case "deadline":
            try {
                taskNum = processDeadline(storedValues, taskNum, line);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Deadline is not added in the correct format! \n" +
                        "deadline <description> /by <date> \n");
            }
            break;
        case "todo":
            try {
                taskNum = processToDo(storedValues, taskNum, line);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("The description after todo cannot be empty!");
            }
            break;
        case "event":
            try {
                taskNum = processEvent(storedValues, taskNum, line);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Event is not added in the correct format! \n" +
                        "event <description> /from <date> /to <date> \n");
            }
            break;
        case "delete":
            try {
                deleteTask(line, storedValues);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Item to delete is not in the list!");
            } catch (IOException e) {
                System.out.println("Error!");
            }
            break;
        default:
            // Commands that are not listed above
            System.out.println("Invalid command, try again! \n");
        }
        return false;
    }

    /***
     * Deletes the task in the list and the txt file, when the user no longer needs the task.
     * @param line The command input from user.
     * @param storedValues List of inputs stored by the user.
     * @throws IOException Thrown when error is detected.
     */
    private static void deleteTask(String line, ArrayList<Task> storedValues) throws IOException {
        int length = line.length();
        String itemToDelete = line.substring(REMOVE_DELETE_NUM, length);
        int posToDelete = Integer.parseInt(itemToDelete);
        Task value = storedValues.get(posToDelete - 1);
        int currLine = 0;

        Storage.deleteTaskInTxt(posToDelete, currLine);

        storedValues.remove(posToDelete - 1);
        formattingLine();
        System.out.println("Noted. I've removed this task: \n" +
                value + "\n" +
                "Now you have " + (taskNum - 1) + " tasks in the list. \n");
        formattingLine();

        taskNum -= 1;
    }


    /***
     * Prior to adding events to the list, processEvent separates the important details
     * within the input for further data storage.
     * @param storedValues List of tasks from user inputs.
     * @param taskNum The existing position in the list.
     * @param line User input to be processed.
     * @return Updated position in the list.
     */
    private static int processEvent(ArrayList<Task> storedValues, int taskNum, String line) {
        int firstForwardSlash = line.indexOf('/');
        String taskName = line.substring(REMOVE_EVENT_NUM, firstForwardSlash - 1);
        String duration = line.substring(firstForwardSlash + REMOVE_FROM_NUM);

        int secondForwardSlash = duration.indexOf('/');
        String startingTime = duration.substring(0, secondForwardSlash - 1);
        String endingTime = duration.substring(secondForwardSlash + REMOVE_TO_NUM);
        Event eventInput = new Event(taskName, startingTime, endingTime);

        taskNum = addTask(storedValues, taskNum, eventInput);
        return taskNum;
    }

    /***
     * Prior to adding events with command toDo to the list, processtoDo separates the
     * important details within the input for data storage.
     * @param storedValues List of tasks from user inputs.
     * @param taskNum The existing position in the list.
     * @param line User input to be processed.
     * @return Updated position in the list.
     */
    private static int processToDo(ArrayList<Task> storedValues, int taskNum, String line) {
        String removeCommand = line.substring(REMOVE_TODO_NUM);
        Todo todoInput = new Todo(removeCommand);
        taskNum = addTask(storedValues, taskNum, todoInput);
        return taskNum;
    }

    /***
     * Prior to adding events with command deadline to the list, processDeadline separates the
     * important details within the input for data storage.
     * @param storedValues List of tasks from user inputs.
     * @param taskNum The existing position in the list.
     * @param line User input to be processed.
     * @return Updated position in the list.
     */
    private static int processDeadline(ArrayList<Task> storedValues, int taskNum, String line) {
        int forwardSlash = line.indexOf('/');
        int endOfTask = forwardSlash - 1;
        int dates = forwardSlash + REMOVE_BY_NUM;
        Deadline deadlineInput = new Deadline(line.substring(9, endOfTask), line.substring(dates));
        taskNum = addTask(storedValues, taskNum, deadlineInput);
        return taskNum;
    }

    /***
     * Adds processed user inputs into the list of stored values.
     * @param storedValues List of tasks from user inputs.
     * @param taskNum The existing position in the list.
     * @param line User input to be processed.
     * @return Updated position in the list.
     */
    private static int addTask(ArrayList<Task> storedValues, int taskNum, Task line) {
        formattingLine();
        System.out.println("Got it. I've added this task: \n" + line.toString() + "\n" +
                "Now you have " + (taskNum + 1) + " tasks in the list.\n");
        formattingLine();
        // Store value in line into list
        storedValues.add(taskNum,line);
        taskNum += 1;
        try {
            Storage.writeToFile(line);
        } catch (IOException e) {
            System.out.println("Something went wrong..." + e.getMessage());
        }
        return taskNum;
    }

    /***
     * Unmarks the task in the list when called.
     * @param storedValues List of tasks from user inputs.
     * @param line User input to be processed.
     * @throws UnmarkOutOfBounds Thrown when the value input is out of bounds.
     * @throws IOException Thrown when other errors are detected.
     */
    private static void unmarkItem(ArrayList<Task> storedValues, String line) throws UnmarkOutOfBounds, IOException {
        int length = line.length();
        String itemToMark = line.substring(REMOVE_UNMARK_NUM, length);
        int numToMark = Integer.parseInt(itemToMark);
        // Unmark the item
        if (taskNum < numToMark) {
            throw new UnmarkOutOfBounds();
        } else {
            unmarkTaskInTxt(storedValues, numToMark);
        }
    }

    /***
     * Unmarks the tast in the txt file when called.
     * @param storedValues List of tasks from user inputs.
     * @param numToMark The row in txt file to be unmarked.
     * @throws IOException Thrown when file cannot be read.
     */
    private static void unmarkTaskInTxt(ArrayList<Task> storedValues, int numToMark) throws IOException {
        storedValues.get(numToMark -1).unmarkAsDone();
        formattingLine();
        System.out.println("OK, I've marked this task as not done yet: \n" +
                storedValues.get(numToMark -1).toString() + "\n");
        formattingLine();

        File dukeInputs = FILEPATH;
        String prevContent = "";
        BufferedReader reader = new BufferedReader(new FileReader(dukeInputs));
        String input = reader.readLine();
        while (input != null) {
            prevContent = prevContent + input + "\n";
            input = reader.readLine();
        }

        char type = storedValues.get(numToMark -1).getClass().toString().substring(6).charAt(0);
        String toReplace = (type + " | 1 | " + storedValues.get(numToMark -1).description);
        String toReplaceWith = (type + " | 0 | " + storedValues.get(numToMark -1).description);
        String newContent = prevContent.replace(toReplace, toReplaceWith);
        FileWriter writer = new FileWriter(dukeInputs);
        writer.write(newContent);
        reader.close();
        writer.close();
    }

    /***
     * Marks the task in the list when called.
     * @param storedValues List of tasks from user inputs.
     * @param line User input to be processed.
     * @param taskNum The task number from user input that is to be marked.
     * @throws MarkOutOfBounds Thrown when the task number exceeds the number of items in list.
     * @throws IOException Thrown when error is detected.
     */
    private static void markItem(ArrayList<Task> storedValues, String line, int taskNum) throws MarkOutOfBounds, IOException {
        int length = line.length();
        String itemToMark = line.substring(REMOVE_MARK_NUM, length);
        int numToMark = Integer.parseInt(itemToMark);
        // Mark the item as complete
        if (taskNum < numToMark) {
            // Means that it is out of bounds
            throw new MarkOutOfBounds();
        } else {
            markTaskInTxt(storedValues, numToMark);
        }
    }

    /***
     * Marks the task in the txt file.
     * @param storedValues List of tasks from user inputs.
     * @param numToMark Task number from user input that is to be marked.
     * @throws IOException Thrown when file cannot be read.
     */
    private static void markTaskInTxt(ArrayList<Task> storedValues, int numToMark) throws IOException {
        storedValues.get(numToMark -1).markAsDone();
        formattingLine();
        System.out.println("Nice! I've marked this task as done: \n"
                + storedValues.get(numToMark -1).toString() + "\n");
        formattingLine();

        File dukeInputs = FILEPATH;
        String prevContent = "";
        BufferedReader reader = new BufferedReader(new FileReader(dukeInputs));
        String input = reader.readLine();
        while (input != null) {
            prevContent = prevContent + input + "\n";
            input = reader.readLine();
        }

        char type = storedValues.get(numToMark -1).getClass().toString().substring(6).charAt(0);
        String toReplace = (type + " | 0 | " + storedValues.get(numToMark -1).description);
        String toReplaceWith = (type + " | 1 | " + storedValues.get(numToMark -1).description);
        String newContent = prevContent.replace(toReplace, toReplaceWith);
        FileWriter writer = new FileWriter(dukeInputs);
        writer.write(newContent);
        reader.close();
        writer.close();
    }

    /***
     * Print all items within the list of stored tasks.
     * @param storedValues List of tasks from user inputs.
     */
    private static void printList(ArrayList<Task> storedValues) {
        int currValue = 0;
        formattingLine();
        System.out.println("Here are the tasks in your list:");
        for (Task value : storedValues) {
            System.out.println((currValue + 1) + "." + value.toString());
            currValue += 1;
        }
        formattingLine();
    }

    /***
     * Outputs the goodbye formatting and message to the user when bye
     * command is called.
     */
    private static void showGoodbye() {
        String bye = "Bye. Hope to see you again soon!\n";
        formattingLine();
        System.out.println(bye);
        formattingLine();
    }

    /**
     * Shows the formatted greetings to the user when called.
     */
    private static void showGreetings() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String hello = " Hello! I'm Duke\n" +
                " What can I do for you?\n";

        System.out.println("Hello from\n" + logo);
        formattingLine();
        System.out.println(hello);
        formattingLine();
    }

    /***
     * Prints the fixed formatting line when called.
     */
    private static void formattingLine() {
        System.out.println(LINE_FORMATTING);
    }
}
