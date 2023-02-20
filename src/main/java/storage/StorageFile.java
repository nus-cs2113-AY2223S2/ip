package storage;

import duke.Deadline;
import duke.Event;
import duke.Task;
import duke.Todo;
import todolist.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import static ui.UI.NAKIRI_AYAME;

public class StorageFile {
    //Data_Path
    public static final String DATA_PATH = "data.txt";
    //Greeting Messages
    public static final String GREETING_MESSAGE_RETURNING_USER = "Hai, Ningensama-tachi! Loading your previous data...";
    public static final String WELCOME_BACK_MESSAGE = "Welcome back, Ningensama! Kon-Nakiri!";
    public static final String GREETING_MESSAGE_NEW_USER = "Hai, Ningensama-tachi! I see you're a new user, Kon-Nakiri!";
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
     * @param taskList an empty TaskList
     * @return Valid TaskList and a File to the stored data if stored data is valid
     * Program terminates if any invalid instructions is found in the stored data
     * Will only load a maximum of 99 tasks, to prevent soft-locking.
     */
    public static File initialiseData(TaskList taskList) {
        File data = new File(DATA_PATH);
        try {
            if (data.createNewFile()) {
                System.out.println(GREETING_MESSAGE_NEW_USER);
            } else {
                System.out.println(GREETING_MESSAGE_RETURNING_USER);
                Scanner in = new Scanner(data);
                boolean fileIsBroken = false;
                while (in.hasNextLine() && taskList.getSize() < 100) {
                    String[] inputMessage = parser.Parser.processInputMessage(in);
                    switch (inputMessage[0]) {
                    case ACTION_MARK_COMPLETE:
                        int taskIndex = parser.Parser.checkActionInputValidity(inputMessage, taskList.getSize());
                        if (taskIndex >= 0) {
                            Task currentTask = taskList.getTask(taskIndex);
                            currentTask.setComplete();
                        } else {
                            fileIsBroken = true;
                        }
                        break;
                    case ACTION_NEW_TODO:
                        String task = parser.Parser.processToDoMessage(inputMessage);
                        if (!task.equals("")) {
                            Todo newTodo = new Todo(task);
                            taskList.addTask(newTodo);
                        } else {
                            fileIsBroken = true;
                        }
                        break;
                    case ACTION_NEW_DEADLINE:
                        inputMessage = parser.Parser.processDeadlineMessage(inputMessage);
                        if (inputMessage.length == 2) {
                            Deadline newDeadline = new Deadline(inputMessage[0], inputMessage[1]);
                            taskList.addTask(newDeadline);
                        } else {
                            fileIsBroken = true;
                        }
                        break;
                    case ACTION_NEW_EVENT:
                        inputMessage = parser.Parser.processEventMessage(inputMessage);
                        if (inputMessage.length == 3) {
                            Event newEvent = new Event(inputMessage[0], inputMessage[1], inputMessage[2]);
                            taskList.addTask(newEvent);
                        } else {
                            fileIsBroken = true;
                        }
                        break;
                    default:
                        fileIsBroken = true;
                    }
                    if (fileIsBroken) {
                        System.out.println(NAKIRI_AYAME + ERROR_LOADING_FILE);
                        java.lang.System.exit(0);
                    }
                }
                System.out.println(WELCOME_BACK_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println(ERROR_WITH_DATA_FILE);
            e.printStackTrace();
        }
        return data;
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
}
