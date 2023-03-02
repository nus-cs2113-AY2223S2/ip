package storage;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;
import todolist.TaskList;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static ui.UI.NAKIRI_AYAME;

public class StorageFile {
    //Data_Path
    public static final String DATA_PATH = "data.txt";
    //Greeting Messages
    public static final String GREETING_MESSAGE_NEW_USER = "Hai, Ningensama-tachi! I see you're a new user, Kon-Nakiri!";
    public static final String GREETING_MESSAGE_LOADING_DATA = "Hai, Ningensama-tachi! Loading your previous data...";
    public static final String GREETING_MESSAGE_RETURNING_USER = "Welcome back, Ningensama! Kon-Nakiri!";
    //Instructions Strings
    public static final String ACTION_MARK_COMPLETE = "mark";
    public static final String ACTION_NEW_TODO = "todo";
    public static final String ACTION_NEW_DEADLINE = "deadline";
    public static final String ACTION_NEW_EVENT = "event";
    public static final String ERROR_WITH_DATA_FILE = "An error occurred with the data file...\n";
    public static final String ERROR_LOADING_FILE = "Why did you edit the data file, Ningen?";

    /**
     * Takes in the stored data specified in DATA_PATH and loads them into the TaskList
     * Used during start-up.
     *
     * @param taskList an empty taskList
     *                 Returns a filled taskList if stored data is valid
     *                 Program terminates if any invalid instructions is found in the stored data
     */
    public static void initialiseData(TaskList taskList) {
        File data = new File(DATA_PATH);
        try {
            if (data.createNewFile()) {
                System.out.println(GREETING_MESSAGE_NEW_USER);
            } else {
                System.out.println(GREETING_MESSAGE_LOADING_DATA);
                Scanner in = new Scanner(data);
                while (in.hasNextLine()) {
                    String[] inputMessage = parser.Parser.processInputMessage(in);
                    boolean fileIsBroken = processDataFile(inputMessage, taskList);
                    if (fileIsBroken) {
                        System.out.println(ERROR_LOADING_FILE);
                        destroyData(data);
                        java.lang.System.exit(0);
                    }
                }
                System.out.println(GREETING_MESSAGE_RETURNING_USER);
            }
        } catch (Exception exception) {
            System.out.println(ERROR_WITH_DATA_FILE);
            exception.printStackTrace();
        }
    }

    /**
     * Used during initialisation of taskList.
     * Adds/marks a single task in the taskList,
     * Also checks for any invalid inputs caused by user editing the data file.
     *
     * @param inputMessage the String Array containing the processed data
     * @param taskList the current state of the TaskList
     * @return true if the instruction was made was valid. False otherwise
     * False indicates the file has been modified by the user illegally.
     */
    private static boolean processDataFile(String[] inputMessage, TaskList taskList) {
        switch (inputMessage[0]) {
        case ACTION_MARK_COMPLETE:
            int taskIndex = parser.Parser.checkActionInputValidity(inputMessage, taskList.getSize());
            if (taskIndex >= 0) {
                Task currentTask = taskList.getTask(taskIndex);
                currentTask.setComplete();
                return false;
            }
            return true;
        case ACTION_NEW_TODO:
            String task = parser.Parser.processToDoMessage(inputMessage);
            if (!task.equals("")) {
                Todo newTodo = new Todo(task);
                taskList.addTask(newTodo);
                return false;
            }
            return true;
        case ACTION_NEW_DEADLINE:
            inputMessage = parser.Parser.processDeadlineMessage(inputMessage);
            if (inputMessage.length == 2) {
                Deadline newDeadline = new Deadline(inputMessage[0], inputMessage[1]);
                taskList.addTask(newDeadline);
                return false;
            }
            return true;
        case ACTION_NEW_EVENT:
            inputMessage = parser.Parser.processEventMessage(inputMessage);
            if (inputMessage.length == 3) {
                Event newEvent = new Event(inputMessage[0], inputMessage[1], inputMessage[2]);
                taskList.addTask(newEvent);
                return false;
            }
            return true;
        default:
            return true;
        }
    }

    /**
     * Updates the data stored in the file specified by DATA_PATH
     * Data is stored as valid instructions that will result in the exact state of the TaskList
     * Called after any edits are made to the TaskList
     *
     * @param taskList the current state of the TaskList
     */
    public static void updateData(TaskList taskList) {
        try {
            FileWriter writer = new FileWriter(DATA_PATH);
            int sizeOfTaskList = taskList.getSize();
            for (int i = 0; i < sizeOfTaskList; i += 1) {
                Task currentTask = taskList.getTask(i);
                String type = currentTask.getType();
                String task = currentTask.getTask();
                String instruction;
                switch (type) {
                case ACTION_NEW_TODO:
                    instruction = String.format("%s %s\n", type, task);
                    break;
                case ACTION_NEW_DEADLINE:
                    Deadline deadline = (Deadline) currentTask;
                    String by = deadline.getDetails();
                    instruction = String.format("%s %s /by %s\n", type, task, by);
                    break;
                case ACTION_NEW_EVENT:
                    Event event = (Event) currentTask;
                    String[] details = event.getDetails();
                    instruction = String.format("%s %s /from %s /to %s\n", type, task, details[0], details[1]);
                    break;
                default:
                    instruction = "Something is wrong I can feel it\n";
                }
                writer.write(instruction);
                if (currentTask.getComplete() == 'X') {
                    instruction = String.format("mark %d\n", i + 1);
                    writer.write(instruction);
                }
            }
            writer.close();
        } catch (Exception e) {
            System.out.println(ERROR_WITH_DATA_FILE);
            e.printStackTrace();
        }
    }

    /**
     * Replaces data.txt contents with NakiriAyame if the file was modified by the user
     *
     * @param data the file containing the previous data
     */
    private static void destroyData(File data) {
        try {
            FileWriter writer = new FileWriter(data, StandardCharsets.UTF_8);
            writer.write(NAKIRI_AYAME);
            writer.close();
        }
        catch (Exception e) {
            System.out.println(ERROR_WITH_DATA_FILE);
            e.printStackTrace();
        }
    }
}
