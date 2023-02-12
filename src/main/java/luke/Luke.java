package luke;

import luke.command.Response;
import luke.command.TaskOrganizer;
import luke.exception.InvalidIndexException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import luke.task.Task;

public class Luke {
    /** An object to manage the responses of LUKE */
    private static Response response;

    /** A scanner object to read in user input */
    private static Scanner scanner;

    /** An object used to manage the tasks added by the user */
    private static TaskOrganizer taskOrganizer;

    /** A list of valid commands */
    private static ArrayList<String> commands;

    private static boolean isAbleToRead() {
        Path dataFolderPath = Path.of("C:/Users/USER/Desktop/NUS/Year_2_Sem_2/CS2113/Individual_Project/data");
        Path IDFilePath = Path.of("C:/Users/USER/Desktop/NUS/Year_2_Sem_2/CS2113/Individual_Project/data/id.txt");
        Path taskListFilePath = Path.of("C:/Users/USER/Desktop/NUS/Year_2_Sem_2/CS2113/Individual_Project/data/taskList.txt");
        File IDFile = new File(IDFilePath.toUri());
        File taskListFile = new File(taskListFilePath.toUri());
        boolean doesExist = (Files.exists(dataFolderPath) && Files.exists(IDFilePath) && Files.exists(taskListFilePath));
        boolean isNotEmpty = (IDFile.length() != 0 && taskListFile.length() != 0);
        return doesExist && isNotEmpty;
    }

    /** Initializes all the objects used in LUKE, says "Hi" to the user */
    private static void initialize() {
        // Initialization
        response = new Response();
        scanner = new Scanner(System.in);
        commands = new ArrayList<String>(
                Arrays.asList("add", "list", "mark", "unmark")
        );

        if (isAbleToRead()) {
            loadPreviousData();
        }
        else {
            loadNewData();
        }

        // Say "Hi" to the user
        response.sayHi();
    }

    /** Loads a completely new set of data */
    private static void loadNewData() {
        taskOrganizer = new TaskOrganizer();
    }

    /** Loads in the taskID and tasks hashmap that is stored in the txt files */
    private static void loadPreviousData() {
        Type taskListType = new TypeToken<HashMap<Integer, Task>>(){}.getType();
        Path IDFile = Path.of("C:/Users/USER/Desktop/NUS/Year_2_Sem_2/CS2113/Individual_Project/data/id.txt");
        Path taskListFile = Path.of("C:/Users/USER/Desktop/NUS/Year_2_Sem_2/CS2113/Individual_Project/data/taskList.txt");
        File temp = new File(IDFile.toUri());
        try {
            String IDFileJson = Files.readString(IDFile);
            String taskListJson = Files.readString(taskListFile);
            int savedID = Integer.parseInt(IDFileJson);
            HashMap<Integer, Task> savedTaskList = new Gson().fromJson(taskListJson, taskListType);
            taskOrganizer = new TaskOrganizer(savedID, savedTaskList);
        } catch (NumberFormatException e) {
            //Handle invalid loading
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /** Closes the scanner and says "Bye" to the user */
    public static void endProgram() {
        scanner.close();
        taskOrganizer.serializeTaskOrganizer();
        response.sayBye();
    }

    /**
     * Takes in a string and echos it.
     *
     * @param userInput The string to be echoed.
     */
    private static void echoString(String userInput) {
        response.printString(userInput);
    }

    /**
     * Extracts out the first word of an input string.
     *
     * @param userInput The input string.
     * @return The first word of the string.
     */
    private static String getFirstWord(String userInput) {
        String[] processedInputs = userInput.split(" ", 2);
        return processedInputs[0];
    }

    /**
     * Removes the first word of an input string and returns the remaining string.
     * Returns null if the string only consist of one word.
     *
     * @param userInput The input string.
     * @return The input string with the first word removed.
     */
    private static String removeFirstWord(String userInput) {
        String[] processedInputs = userInput.split(" ", 2);
        if (processedInputs.length == 1) {
            return null;
        }
        return processedInputs[1];
    }

    /**
     * Checks if the input string is a valid command.
     *
     * @param keyWord The input string.
     * @return True if the input string is a valid keyword, False otherwise.
     */
    private static boolean isCommand(String keyWord) {
        return commands.contains(keyWord);
    }

    /** Informs the user that he/ she have entered an invalid command */
    private static void handleInvalidCommand() {
        response.printInvalidCommand();
    }

    /** Informs the user that index entered is out of bounds */
    private static void handleOutOfBounds() {
        response.printOutOfBounds();
    }

    /**
     * Adds a task to the TaskOrganizer based on information stored in the input string.
     *
     * @param taskInfo Input string consisting of all relevant task information such as type, name, and relevant dates.
     */
    private static void executeAddTask(String taskInfo) {
        // Check if taskInfo is empty
        if (taskInfo == null) {
            handleInvalidCommand();
            return;
        }

        String taskType = getFirstWord(taskInfo);

        //Check if the task type entered by the user is valid
        if (!taskOrganizer.isTaskType(taskType)) {
            handleInvalidCommand();
            return;
        }

        String taskDetail = removeFirstWord(taskInfo);

        //Checks if the name of the task is empty
        if (taskDetail == null) {
            handleInvalidCommand();
            return;
        }

        String taskName = getFirstWord(taskDetail);
        String taskDate = removeFirstWord(taskDetail);
        boolean isAdded = taskOrganizer.addTask(taskType, taskName, taskDate);

        //Check if the task has been added without any errors
        if (!isAdded) {
            handleInvalidCommand();
            return;
        }

        response.printAddTask(taskName);
    }

    /** Prints out a list of tasks in the TaskOrganizer */
    private static void executeListTask() {
        //Check if the TaskOrganizer is empty
        if (taskOrganizer.isEmpty()) {
            response.printEmptyList();
            return;
        }
        response.printTaskList(taskOrganizer.getTaskList());
    }

    /**
     * Mark the specified task as completed.
     *
     * @param taskSerialNumber The serial number of the task to be marked.
     */
    private static void executeMarkTask(String taskSerialNumber) {
        if (taskSerialNumber == null) {
            handleInvalidCommand();
            return;
        }
        try {
            int serialNumber = Integer.parseInt(taskSerialNumber);
            if (taskOrganizer.isOutOfBounds(serialNumber)) {
                throw new InvalidIndexException();
            }
            taskOrganizer.markTask(serialNumber);
            response.printMarkTask(taskOrganizer.getTaskByID(serialNumber));
        } catch (NumberFormatException e) {
            handleInvalidCommand();
        } catch (InvalidIndexException e) {
            handleOutOfBounds();
        }
    }

    /**
     * Unmarks the specified task.
     *
     * @param taskSerialNumber The serial number of the task to be unmarked.
     */
    private static void executeUnmarkTask(String taskSerialNumber) {
        if (taskSerialNumber == null) {
            handleInvalidCommand();
            return;
        }
        try {
            int serialNumber = Integer.parseInt(taskSerialNumber);
            if (taskOrganizer.isOutOfBounds(serialNumber)) {
                throw new InvalidIndexException();
            }
            taskOrganizer.unmarkTask(serialNumber);
            response.printUnmarkTask(taskOrganizer.getTaskByID(serialNumber));
        } catch (NumberFormatException e) {
            handleInvalidCommand();
        } catch (InvalidIndexException e) {
            handleOutOfBounds();
        }
    }

    /**
     * This function takes in the command keyword and description and executes the specified command.
     *
     * @param command The command keyword indicating the type of command to execute.
     * @param description The string specifying the details of the command.
     */
    private static void executeCommand(String command, String description) {
        switch (command) {
        case "add":
            executeAddTask(description);
            break;
        case "list":
            executeListTask();
            break;
        case "mark":
            executeMarkTask(description);
            break;
        default:
            executeUnmarkTask(description);
            break;
        }
    }

    /** Continuously reads in the user input until the user keys in "bye" */
    private static void readUserInput() {
        while (true) {
            String userInput = scanner.nextLine();
            //If the user input is bye, exit the program
            if (userInput.equalsIgnoreCase("bye")) {
                return;
            }

            //Checks if the user is trying to key in a command, else echo the user input
            String firstWord = getFirstWord(userInput);
            if (isCommand(firstWord)) {
                String description = removeFirstWord(userInput);
                executeCommand(firstWord, description);
            } else {
                echoString(userInput);
            }
        }
    }

    public static void main(String[] args) {
        initialize();
        readUserInput();
        endProgram();
    }
}
