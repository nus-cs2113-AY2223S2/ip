package max.data;

import max.Ui.Ui;
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

public class Storage {
    private static final String DELIMITER = " -- ";

    // These constants define the index position of task arguments in the token array
    private static final int TASK_TYPE_INDEX = 0;
    private static final int TASK_DONE_INDEX = 1;
    private static final int TASK_DESCRIPTION_INDEX = 2;
    private static final int TASK_START_DATE_INDEX = 3;
    private static final int TASK_DUE_DATE_INDEX = 3;
    private static final int TASK_END_DATE_INDEX = 4;
    private static final String TASK_NEWLINE_TOKEN = "\n";
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
    private static final String MESSAGE_TASK_FAIL_LOAD = "There are tasks I couldn't load ><\"";
    private static final String MESSAGE_TASK_REINPUT = "You MUST manually input these tasks again:";
    private static final String MESSAGE_BAD_TASK = "\nBad task: ";
    private static final String MESSAGE_COLON_SEPARATOR = ": ";
    private static Ui ui;
    Path maxDataDirectory;

    /**
     * Constructs a Storage class instance to help with saving/loading of data
     */
    public Storage() {
        ui = new Ui();
        maxDataDirectory = Paths.get(TASK_CURR_DIR, TASK_DATA_DIR);
        // Create data directory if it does not exist
        try {
            Files.createDirectories(maxDataDirectory);
        } catch (IOException e) {
            ui.printError(e.getMessage());
        }
    }

    private boolean doesFileExist(String filename) {
        // Check if file exists
        // If it doesn't exist, then create it but return does not exist
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

    private String tokenizeTasks(ArrayList<Task> arrayList) {
        String tokenizedString = "";
        // Each task will be tokenized in this format:
        // <Label> -- <isDone> -- <description> [ -- <otherArgs> ...]\n
        // The command validator filters our rogue usage of '--' between words
        // This guarantees that all our task inputs can be properly delimited by '--'
        // Hence, the choosing of '--' to delimit the persistent data
        for (Task task : arrayList) {
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
        if (errors.size() == 0) {
            return;
        }
        ui.notifyImportant();
        ui.printError(MESSAGE_TASK_FAIL_LOAD);
        ui.printMessage(MESSAGE_TASK_REINPUT);
        int count = 1;
        for (String error : errors) {
            ui.printMessage(count + MESSAGE_COLON_SEPARATOR + error);
            ++count;
        }

    }

    private String buildErrorMessage(String errorMessage, String corruptedTaskString) {
        errorMessage += MESSAGE_BAD_TASK + corruptedTaskString;
        return errorMessage;
    }

    private ArrayList<Task> detokenizeTasks(String tokenizedString) {
        // detokenizeTasks will try to load the Tasks by best-effort
        // Instead of throwing an exception, problematic taskStrings will be skipped
        // All problematic lines will be printed afterwards
        ArrayList<Task> taskArrayList = new ArrayList<>();
        String[] tasks = tokenizedString.split(TASK_NEWLINE_TOKEN);

        // Edge case: Handle empty file
        if (tasks.length == 1 && tasks[0].length() == 0) {
            return taskArrayList;
        }

        // Holds the error messages for each bad task for printing
        ArrayList<String> badTaskStrings = new ArrayList<>();
        for (String taskString : tasks) {
            String[] taskComponents = taskString.split(DELIMITER);

            // Sanity check - taskComponents should minimally have 3 arguments
            // Its task label, done status and description
            if (taskComponents.length < 3) {
                String errorMessage = buildErrorMessage(EXCEPTION_BAD_TASK_LEN, taskString);
                badTaskStrings.add(errorMessage);
                continue;
            }

            // New task fields
            Task newTask;
            String taskType = taskComponents[TASK_TYPE_INDEX];
            String taskDescription = taskComponents[TASK_DESCRIPTION_INDEX];
            String taskStart;
            String taskEnd;
            boolean isTaskDone = taskComponents[TASK_DONE_INDEX].equals(TASK_DONE_TOKEN);

            switch (taskType) {
            case TASK_TODO_TOKEN:
                newTask = new Todo(taskDescription);
                break;
            case TASK_DEADLINE_TOKEN:
                try {
                    taskStart = taskComponents[TASK_DUE_DATE_INDEX];
                } catch (IndexOutOfBoundsException exception) {
                    String errorMessage = buildErrorMessage(EXCEPTION_BAD_TASK_LEN, taskString);
                    badTaskStrings.add(errorMessage);
                    continue;
                }
                newTask = new Deadline(taskDescription, taskStart);
                break;
            case TASK_EVENT_TOKEN:
                try {
                    taskStart = taskComponents[TASK_START_DATE_INDEX];
                    taskEnd = taskComponents[TASK_END_DATE_INDEX];
                } catch (IndexOutOfBoundsException exception) {
                    String errorMessage = buildErrorMessage(EXCEPTION_BAD_TASK_LEN, taskString);
                    badTaskStrings.add(errorMessage);
                    continue;
                }
                newTask = new Event(taskDescription, taskStart, taskEnd);
                break;
            default:
                String errorMessage = buildErrorMessage(EXCEPTION_BAD_TASK_LEN, taskString);
                badTaskStrings.add(errorMessage);
                continue;
            }

            // If isTaskDone is corrupted, it will default to false
            if (isTaskDone) {
                newTask.markAsDone();
            } else {
                newTask.markAsUndone();
            }

            taskArrayList.add(newTask);
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

    public void saveTasksToDisk(ArrayList<Task> taskList) {
        String tokenizedTasks = tokenizeTasks(taskList);
        writeToDisk(tokenizedTasks, TASK_FILENAME);
    }

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
