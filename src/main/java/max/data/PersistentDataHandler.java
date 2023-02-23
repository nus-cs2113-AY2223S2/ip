package max.data;

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

public class PersistentDataHandler {
    // TODO refactor out the tokenizers into a seperate class

    private static final String delimiter = " -- ";

    // These constants define the index position of task arguments in the token array
    private static final int TASK_TYPE_INDEX = 0;
    private static final int TASK_DONE_INDEX = 1;
    private static final int TASK_DESCRIPTION_INDEX = 2;
    private static final int TASK_START_DATE_INDEX = 3;
    private static final int TASK_DUE_DATE_INDEX = 3;
    private static final int TASK_END_DATE_INDEX = 4;
    private static final String TASK_FILENAME = "tasklist";
    Path maxDataDirectory;

    public PersistentDataHandler() {
        maxDataDirectory = Paths.get(".", "data");
        // Create data directory if it does not exist
        try {
            Files.createDirectories(maxDataDirectory);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean doesFileExist(String filename) {
        // Check if file exists
        // If it doesn't exist, then create it but return does not exist
        Path pathToFilename = maxDataDirectory.resolve(filename + ".txt");
        File maxDataFile = new File(pathToFilename.toUri());
        boolean isDataExist = maxDataFile.exists();
        if (!isDataExist) {
            try {
                maxDataFile.createNewFile();
            } catch (IOException exception) {
                System.out.println("Failed to create new file!");
                System.out.println(exception.getMessage());
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
            String taskString = task.getTaskLabel() + delimiter;
            String isDone = task.isDone() ? "1" : "0";
            taskString += isDone + delimiter;
            taskString += task.getRawDescription();
            if (task instanceof Event) {
                // Task has other arguments. Add them in
                taskString += delimiter;
                taskString += ((Event) task).getEventFrom() + delimiter;
                taskString += ((Event) task).getEventTo();
            } else if (task instanceof Deadline) {
                // Task has other arguments. Add them in
                taskString += delimiter;
                taskString += ((Deadline) task).getDueDate();
            }
            tokenizedString = tokenizedString.concat(taskString + '\n');
        }
        return tokenizedString;
    }

    private void printErrorMessages(ArrayList<String> errors) {
        if (errors.size() == 0) {
            return;
        }
        System.out.println("Awoo? There are tasks I couldn't load ><");
        System.out.println("You'll have to manually input these tasks again:");
        int count = 1;
        for (String error : errors) {
            System.out.println(count + ": " + error);
            ++count;
        }

    }

    private String buildErrorMessage(DataError error, String corruptedTaskString) {
        String errorMessage = error.getErrorMessage();
        errorMessage += "\nBad task: " + corruptedTaskString;
        return errorMessage;
    }

    private ArrayList<Task> detokenizeTasks(String tokenizedString) {
        // detokenizeTasks will try to load the Tasks by best-effort
        // Instead of throwing an exception, problematic taskStrings will be skipped
        // All problematic lines will be printed afterwards
        ArrayList<Task> taskArrayList = new ArrayList<>();
        String[] tasks = tokenizedString.split("\n");

        // Edge case: Handle empty file
        if (tasks.length == 1 && tasks[0].length() == 0) {
            return taskArrayList;
        }

        // Holds the error messages for each bad task for printing
        ArrayList<String> badTaskStrings = new ArrayList<>();
        for (String taskString : tasks) {
            String[] taskComponents = taskString.split(" -- ");

            // Sanity check - taskComponents should minimally have 3 arguments
            // Its task label, done status and description
            if (taskComponents.length < 3) {
                String errorMessage = buildErrorMessage(DataError.TASK_INVALID_LENGTH, taskString);
                badTaskStrings.add(errorMessage);
                continue;
            }

            // New task fields
            Task newTask;
            String taskType = taskComponents[TASK_TYPE_INDEX];
            String taskDescription = taskComponents[TASK_DESCRIPTION_INDEX];
            String taskStart = "";
            String taskEnd = "";
            boolean isTaskDone = taskComponents[TASK_DONE_INDEX].equals("1");
            switch (taskType) {
            case "T":
                newTask = new Todo(taskDescription);
                break;
            case "D":
                try {
                    taskStart = taskComponents[TASK_DUE_DATE_INDEX];
                } catch (IndexOutOfBoundsException exception) {
                    String errorMessage = buildErrorMessage(DataError.TASK_INVALID_LENGTH, taskString);
                    badTaskStrings.add(errorMessage);
                    continue;
                }

                newTask = new Deadline(taskDescription, taskStart);
                break;
            case "E":
                try {
                    taskStart = taskComponents[TASK_START_DATE_INDEX];
                    taskEnd = taskComponents[TASK_END_DATE_INDEX];
                } catch (IndexOutOfBoundsException exception) {
                    String errorMessage = buildErrorMessage(DataError.TASK_INVALID_LENGTH, taskString);
                    badTaskStrings.add(errorMessage);
                    continue;
                }

                newTask = new Event(taskDescription, taskStart, taskEnd);
                break;
            default:
                String errorMessage = buildErrorMessage(DataError.TASK_INVALID_LABEL, taskString);
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
        Path pathToFilename = maxDataDirectory.resolve(filename + ".txt");
        File maxDataFile = new File(pathToFilename.toUri());
        try {
            FileWriter writer = new FileWriter(maxDataFile.getAbsolutePath());
            writer.write(data);
            writer.close();
        } catch (IOException exception) {
            System.out.println("Failed to write data!");
            System.out.println(exception.getMessage());
        }
    }

    private String loadFromDisk(String filename) {
        Path pathToFileName = maxDataDirectory.resolve(filename + ".txt");
        File maxDataFile = new File(pathToFileName.toUri());
        StringBuilder data = new StringBuilder();
        try {
            Scanner reader = new Scanner(maxDataFile);
            while (reader.hasNextLine()) {
                data.append(reader.nextLine()).append("\n");
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
