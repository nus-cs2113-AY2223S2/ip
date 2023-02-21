import java.util.ArrayList;
import java.nio.file.Paths;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

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
    public static int taskNum = 0;
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
        while (!Parser.hasProcessedAllInputs(line, storedValues)) {
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
     * Outputs the goodbye formatting and message to the user when bye
     * command is called.
     */
    private static void showGoodbye() {
        String bye = "Bye. Hope to see you again soon!\n";
        TaskList.formattingLine();
        System.out.println(bye);
        TaskList.formattingLine();
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
        TaskList.formattingLine();
        System.out.println(hello);
        TaskList.formattingLine();
    }

}
