package storage;

import duke.Deadline;
import duke.Event;
import duke.Task;
import duke.Todo;
import todolist.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

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
    public static final String ERROR_LOADING_FILE = "Why did you edit the data file?";

    public static File initialiseData(TaskList taskList) {
        File data = new File(DATA_PATH);
        try {
            if (data.createNewFile()) {
                System.out.println(GREETING_MESSAGE_NEW_USER);
            } else {
                System.out.println(GREETING_MESSAGE_RETURNING_USER);
                Scanner in = new Scanner(data);
                boolean fileIsBroken = false;
                while (in.hasNextLine()) {
                    String[] inputMessage = parser.Parser.processInputMessage(in);
                    switch (inputMessage[0]) {
                    case ACTION_MARK_COMPLETE:
                        int taskIndex = parser.Parser.checkActionInputValidity(inputMessage, taskList.getSize());
                        Task currentTodo = taskList.getTask(taskIndex);
                        currentTodo.setComplete();
                        break;
                    case ACTION_NEW_TODO:
                        String task = parser.Parser.processToDoMessage(inputMessage);
                        Todo newTodo = new Todo(task);
                        taskList.addTask(newTodo);
                        break;
                    case ACTION_NEW_DEADLINE:
                        inputMessage = parser.Parser.processDeadlineMessage(inputMessage);
                        Deadline newDeadline = new Deadline(inputMessage[0], inputMessage[1]);
                        taskList.addTask(newDeadline);
                        break;
                    case ACTION_NEW_EVENT:
                        inputMessage = parser.Parser.processEventMessage(inputMessage);
                        Event newEvent = new Event(inputMessage[0], inputMessage[1], inputMessage[2]);
                        taskList.addTask(newEvent);
                        break;
                    default:
                        fileIsBroken = true;
                    }
                    if (fileIsBroken) {
                        System.out.println(ERROR_LOADING_FILE);
                        //TODO: introduce clear feature to wipe everything, to force-use here + use as feature.
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

    public static void updateData(TaskList taskList) {
        Task currentTodo;
        try {
            FileWriter writer = new FileWriter(DATA_PATH);
            int sizeOfTodoList = taskList.getSize();
            for (int i = 0; i < sizeOfTodoList; i += 1) {
                currentTodo = taskList.getTask(i);
                String type = currentTodo.getType();
                String task = currentTodo.getTask();
                String instruction;
                switch (type) {
                case ACTION_NEW_TODO:
                    instruction = String.format("%s %s\n", type, task);
                    break;
                case ACTION_NEW_DEADLINE:
                    Deadline deadline = (Deadline) currentTodo;
                    String by = deadline.getDetails();
                    instruction = String.format("%s %s /by %s\n", type, task, by);
                    break;
                case ACTION_NEW_EVENT:
                    Event event = (Event) currentTodo;
                    String[] details = event.getDetails();
                    instruction = String.format("%s %s /from %s /to %s\n", type, task, details[0], details[1]);
                    break;
                default:
                    instruction = "Something is wrong I can feel it\n";
                }
                writer.write(instruction);
                if (currentTodo.getComplete() == 'X') {
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
