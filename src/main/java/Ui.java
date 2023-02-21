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
}
