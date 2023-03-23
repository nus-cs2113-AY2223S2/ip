import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    /***
     * Reads in the user inputs and processes the commands.
     */
    static void acceptUserInputs(File data) {
        try {
            readExistingData(data, Duke.storedValues);
        } catch (FileNotFoundException e) {
            System.out.println("File is not found!");
        }
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (!Parser.hasProcessedAllInputs(line, Duke.storedValues)) {
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
            default:
                System.out.println("Line in txt file cannot be read!");
                break;
            }
        }
    }

    /***
     * Function splits the command before and after the first slash spotted in the command into useful
     * information that will be subsequently used.
     * @param slash The position of the slash in the Task stored
     * @param command The list of values stored, with the first value being the command prior to the slash
     *                and the second value being the command after the slash.
     * @return Returns the list of values that has been split.
     */
    private static String[] splitCommand(int slash, String command) {
        String beforeSlash = command.substring(0, slash-1);
        String afterSlash = command.substring(slash+2);
        String[] content = new String[2];
        content[0] = beforeSlash;
        content[1] = afterSlash;

        return content;
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
        String eventInputs[] = splitCommand(firstSlash, extractType[4]);
        String eventName = eventInputs[0];
        String eventDuration = eventInputs[1];

        int secondSlash = eventDuration.indexOf("|");
        String secondEventInputs[] = splitCommand(secondSlash, eventDuration);
        String eventFrom = secondEventInputs[0];
        String eventTo = secondEventInputs[1];

        Event addInputE = new Event(eventName, eventFrom, eventTo);
        storedValues.add(Duke.taskNum,addInputE);
        if (marked.equals("1")) {
            storedValues.get(Duke.taskNum).markAsDone();
        }
        Duke.taskNum += 1;
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
        String deadlineInputs[] = splitCommand(slash, extractType[4]);
        String deadlineTask = deadlineInputs[0];
        String deadlineBy = deadlineInputs[1];
        Deadline addInputD = new Deadline(deadlineTask, deadlineBy);
        storedValues.add(Duke.taskNum,addInputD);
        if (marked.equals("1")) {
            storedValues.get(Duke.taskNum).markAsDone();
        }
        Duke.taskNum += 1;
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
        storedValues.add(Duke.taskNum,addInputT);
        if (marked.equals("1")) {
            storedValues.get(Duke.taskNum).markAsDone();
        }
        Duke.taskNum += 1;
    }

    /***
     * Function provides the user a visual confirmation that the item to mark has been completed, before
     * proceeding with storing the marked data.
     * @param storedValues The existing list by the user.
     * @param numToMark The task that the user is looking to mark.
     */
    static void informUserTaskMarked(ArrayList<Task> storedValues, int numToMark) {
        try {
            storedValues.get(numToMark -1).markAsDone();
            TaskList.formattingLine();
            System.out.println("Nice! I've marked this task as done: \n"
                    + storedValues.get(numToMark -1).toString() + "\n");
            TaskList.formattingLine();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Input index is out of bounds!");
        }

    }

    /***
     * Function provides the user a visual confirmation that the item to unmark has been completed, before
     * proceeding with storing the unmarked data.
     * @param storedValues The existing list by the user.
     * @param numToMark The task that the user is looking to unmark.
     */
    static void informUserTaskUnmarked(ArrayList<Task> storedValues, int numToMark) {
        try {
            storedValues.get(numToMark - 1).unmarkAsDone();
            TaskList.formattingLine();
            System.out.println("OK, I've marked this task as not done yet: \n" +
                    storedValues.get(numToMark - 1).toString() + "\n");
            TaskList.formattingLine();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Input index is out of bounds!");
        }
    }
    /***
     * Given the word to search for, this function checks if there are any tasks within the tasklist that
     * has the keyword the user is requesting for.
     * @param keywordList The list of tasks with the given keyword.
     * @param wordToFind The keyword that has been searched by the user.
     */
    static void printWordToFind(ArrayList<Task> keywordList, String wordToFind) {
        if (keywordList.size() == 0) {
            // Means that there are no common matches
            System.out.println("Sorry, the keyword " + wordToFind + " did not appear in your list of tasks.");
        } else {
            String textToPrint = "Here are the matches in your list!";
            TaskList.printList(keywordList,textToPrint);
        }
    }
}
