package max.data;

import max.ui.Ui;
import max.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage is the interface for MAX to load/save data.
 * <p>
 * Internally, Storage has functions to tokenize & de-tokenize task data from MAX.
 * Storage will automatically create a data folder to store data for persistence.
 */
public class Storage {
    private static final String DELIMITER = " -- ";

    // These constants define the index position of task arguments in the token array
    private static final int TASK_TYPE_INDEX = 0;
    private static final int TASK_DONE_INDEX = 1;
    private static final int TASK_DESCRIPTION_INDEX = 2;
    private static final int TASK_START_DATE_INDEX = 3;
    private static final int TASK_DUE_DATE_INDEX = 3;
    private static final int TASK_END_DATE_INDEX = 4;

    // Index and size integer constants
    private static final int SIZE_EMPTY = 0;
    private static final int ZERO_INDEX_START = 0;
    private static final int ONE_INDEX_START = 1;
    private static final int MIN_TASK_ARG_LENGTH = 3;
    private static final int EMPTY_FILE_TASK_LENGTH = 1;

    // Delimiter constants
    private static final String TASK_NEWLINE_TOKEN = System.lineSeparator();
    private static final String TASK_DONE_TOKEN = "1";
    private static final String TASK_UNDONE_TOKEN = "0";
    private static final String TASK_TODO_TOKEN = "T";
    private static final String TASK_EVENT_TOKEN = "E";
    private static final String TASK_DEADLINE_TOKEN = "D";
    private static final String TASK_FILENAME = "tasklist";
    private static final String TASK_FILE_EXT = ".txt";
    private static final String TASK_DATA_DIR = "data";
    private static final String TASK_CURR_DIR = ".";

    // The following constants define set messages
    private static final String EXCEPTION_MAKE_FILE_FAIL = "Failed to create new file!";
    private static final String EXCEPTION_WRITE_DATA_FAIL = "Failed to write data!";
    private static final String EXCEPTION_BAD_TASK_LEN = "Invalid task token length!";
    private static final String EXCEPTION_MISSING_TOKEN = "Missing a task token!";
    private static final String EXCEPTION_UNKNOWN_LOAD_ERROR = "Unknown error encountered while loading data!";
    private static final String MESSAGE_TASK_FAIL_LOAD = "There are tasks I couldn't load ><\"";
    private static final String MESSAGE_TASK_REINPUT = "You MUST manually input these tasks again:";
    private static final String MESSAGE_BAD_TASK = "\nBad task: ";
    private static final String MESSAGE_COLON_SEPARATOR = ": ";
    private static Ui ui;
    private Path maxDataDirectory;

    /**
     * Constructs a Storage class instance to help with saving/loading of data
     */
    public Storage() {
        ui = new Ui();
        maxDataDirectory = Paths.get(TASK_CURR_DIR, TASK_DATA_DIR);
        // Create data directory if it does not exist
        try {
            Files.createDirectories(maxDataDirectory);
        } catch (IOException exception) {
            ui.printError(exception.getMessage());
        }
    }

    /**
     * Check if a file exists in the ./data directory <br>
     * Notify the user via console on errors, if any. <br>
     * Automatically create the file if the file does not exist.
     *
     * @param filename Filename to check
     * @return True if file exists, else False
     */
    private boolean doesFileExist(String filename) {
        Path pathToFilename = maxDataDirectory.resolve(filename + TASK_FILE_EXT);
        File maxDataFile = new File(pathToFilename.toUri());
        boolean isDataExist = maxDataFile.exists();
        if (!isDataExist) {
            try {
                maxDataFile.createNewFile();
            } catch (IOException exception) {
                ui.notifyImportant();
                ui.printError(EXCEPTION_MAKE_FILE_FAIL);
                ui.printMessage(exception.getMessage());
            }
        }
        return isDataExist;
    }

    /**
     * Takes in a list of Tasks to tokenize into a string <br>
     * <p>
     * Each task will be tokenized in this format: <br>
     * {Label} -- {isDone} -- {description} { -- [otherArgs] ...} <br>
     * <p>
     * The command validator filters out rogue usage of '--' between words. <br>
     * This guarantees that all our task inputs can be properly delimited by '--' <br>
     *
     * @param taskList
     * @return string of all the tokenized tasks
     */
    private String tokenizeTasks(ArrayList<Task> taskList) {
        String tokenizedString = "";

        for (Task task : taskList) {
            String taskString = task.getTaskLabel() + DELIMITER;
            String isDone = task.isDone() ? TASK_DONE_TOKEN : TASK_UNDONE_TOKEN;
            taskString += isDone + DELIMITER;
            taskString += task.getRawDescription();
            if (task instanceof Event) {
                // Task has other arguments. Add them in
                taskString += DELIMITER;
                taskString += ((Event) task).getEventFrom() + DELIMITER;
                taskString += ((Event) task).getEventTo();
            } else if (task instanceof Deadline) {
                // Task has other arguments. Add them in
                taskString += DELIMITER;
                taskString += ((Deadline) task).getDueDate();
            }
            tokenizedString = tokenizedString.concat(taskString + TASK_NEWLINE_TOKEN);
        }
        return tokenizedString;
    }

    private void printErrorMessages(ArrayList<String> errors) {
        if (errors.size() == SIZE_EMPTY) {
            return;
        }
        ui.notifyImportant();
        ui.printError(MESSAGE_TASK_FAIL_LOAD);
        ui.printMessage(MESSAGE_TASK_REINPUT);
        int count = ONE_INDEX_START;
        for (String error : errors) {
            ui.printMessage(count + MESSAGE_COLON_SEPARATOR + error);
            count += 1;
        }

    }

    private Todo buildTodo(String[] taskComponents) {
        String taskDescription = taskComponents[TASK_DESCRIPTION_INDEX];
        return new Todo(taskDescription);
    }

    private Deadline buildDeadline(String[] taskComponents) throws StorageLoadException {
        String taskDescription = taskComponents[TASK_DESCRIPTION_INDEX];
        String taskStart;
        try {
            taskStart = taskComponents[TASK_DUE_DATE_INDEX];
        } catch (IndexOutOfBoundsException exception) {
            throw new StorageLoadException(EXCEPTION_MISSING_TOKEN);
        }
        return new Deadline(taskDescription, taskStart);
    }

    private Event buildEvent(String[] taskComponents) throws StorageLoadException {
        String taskDescription = taskComponents[TASK_DESCRIPTION_INDEX];
        String taskStart;
        String taskEnd;
        try {
            taskStart = taskComponents[TASK_START_DATE_INDEX];
            taskEnd = taskComponents[TASK_END_DATE_INDEX];
        } catch (IndexOutOfBoundsException exception) {
            throw new StorageLoadException(EXCEPTION_MISSING_TOKEN);
        }
        return new Event(taskDescription, taskStart, taskEnd);
    }

    private void markNewTask(Task newTask, boolean isTaskDone) {
        if (isTaskDone) {
            newTask.markAsDone();
        } else {
            newTask.markAsUndone();
        }
    }

    private Task detokenizeTaskString(String taskString) throws StorageLoadException {
        String[] taskComponents = taskString.split(DELIMITER);
        // Sanity check - taskComponents should minimally have length 3 - task label, done status and description
        if (taskComponents.length < MIN_TASK_ARG_LENGTH) {
            throw new StorageLoadException(EXCEPTION_BAD_TASK_LEN);
        }

        Task newTask;
        String taskType = taskComponents[TASK_TYPE_INDEX];
        boolean isTaskDone = taskComponents[TASK_DONE_INDEX].equals(TASK_DONE_TOKEN);

        switch (taskType) {
        case TASK_TODO_TOKEN:
            newTask = buildTodo(taskComponents);
            break;
        case TASK_DEADLINE_TOKEN:
            newTask = buildDeadline(taskComponents);
            break;
        case TASK_EVENT_TOKEN:
            newTask = buildEvent(taskComponents);
            break;
        default:
            throw new StorageLoadException(EXCEPTION_UNKNOWN_LOAD_ERROR);
        }
        markNewTask(newTask, isTaskDone);
        return newTask;
    }

    /**
     * Processes a tokenized string and formats it into a list of Tasks <br>
     * <p>
     * The Tasks will be loaded on a best-effort basis. <br>
     * Instead of throwing an exception, problematic taskStrings will be <i>skipped</i>. <br>
     * All problematic tokens will be printed afterwards for the user's information.
     *
     * @param tokenizedString tokenized string format of the tasks
     * @return List of Tasks that were successfully detokenized
     */
    private ArrayList<Task> detokenizeTasks(String tokenizedString) {

        ArrayList<Task> taskArrayList = new ArrayList<>();
        String[] tasks = tokenizedString.split(TASK_NEWLINE_TOKEN);

        // Edge case: Handle empty file
        if (tasks.length == EMPTY_FILE_TASK_LENGTH && tasks[ZERO_INDEX_START].length() == SIZE_EMPTY) {
            return taskArrayList;
        }

        // Holds the error messages for each bad task for printing
        ArrayList<String> badTaskStrings = new ArrayList<>();
        for (String taskString : tasks) {
            try {
                Task detokenizedTask = detokenizeTaskString(taskString);
                taskArrayList.add(detokenizedTask);
            } catch (StorageLoadException exception) {
                badTaskStrings.add(exception.getMessage());
            }
        }
        printErrorMessages(badTaskStrings);
        return taskArrayList;
    }

    private void writeToDisk(String data, String filename) {
        Path pathToFilename = maxDataDirectory.resolve(filename + TASK_FILE_EXT);
        File maxDataFile = new File(pathToFilename.toUri());
        try {
            FileWriter writer = new FileWriter(maxDataFile.getAbsolutePath());
            writer.write(data);
            writer.close();
        } catch (IOException exception) {
            System.out.println(EXCEPTION_WRITE_DATA_FAIL);
            System.out.println(exception.getMessage());
        }
    }

    private String loadFromDisk(String filename) {
        Path pathToFileName = maxDataDirectory.resolve(filename + TASK_FILE_EXT);
        File maxDataFile = new File(pathToFileName.toUri());
        StringBuilder data = new StringBuilder();
        try {
            Scanner reader = new Scanner(maxDataFile);
            while (reader.hasNextLine()) {
                data.append(reader.nextLine()).append(TASK_NEWLINE_TOKEN);
            }
        } catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
        return data.toString();
    }

    /**
     * Takes in a list of tasks to save on the user's hard-drive
     *
     * @param taskList list of tasks to be saved to disk
     */
    public void saveTasksToDisk(ArrayList<Task> taskList) {
        String tokenizedTasks = tokenizeTasks(taskList);
        writeToDisk(tokenizedTasks, TASK_FILENAME);
    }

    /**
     * Returns a list of tasks that exist on the user's hard-drive
     *
     * @return A list of successfully loaded tasks from the disk,
     * or an empty list if no task data file was found
     */
    public ArrayList<Task> loadTasksFromDisk() {
        ArrayList<Task> processedTaskData;
        if (doesFileExist(TASK_FILENAME)) {
            String tokenizedTaskString = loadFromDisk(TASK_FILENAME);
            processedTaskData = detokenizeTasks(tokenizedTaskString);
            return processedTaskData;
        }
        processedTaskData = new ArrayList<>();
        return processedTaskData;
    }
}
