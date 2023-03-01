package io.github.haoyangw.rica.storage;

import io.github.haoyangw.rica.exception.RicaStorageException;
import io.github.haoyangw.rica.task.Task;

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
 * Handles the storage of Rica's data into a text file and the retrieval of stored
 *   data from a saved text file to bring back Rica's memory. Provides saveTasks()
 *   to save Rica's list of Tasks, and getSavedTasks() to read the saved list of
 *   Tasks(if any) from storage.
 */
public class StorageManager {
    private static final String DEFAULT_DATA_PATH = "data/tasks.txt";
    private static final String INVALID_DATA_PATH_GIVEN_ERROR = " Empty data path given, what am I supposed to read xD";
    private static final String DATA_FILE_NOT_FOUND_ERROR = " Cannot find the data file, you're gonna have to deal with my amnesia today my friend xP";
    private static final String FAILED_TO_CREATE_DATA_FILE_ERROR = " Darn, can't even create a data file to store my memory. Not my day! xP";
    private final String dataPath;

    public StorageManager() {
        this(StorageManager.DEFAULT_DATA_PATH);
    }

    public StorageManager(String dataPath) {
        this.dataPath = dataPath;
    }

    /**
     * Creates all directories along the data storage path if they don't already exist.
     *
     * @param dataPath Desired data storage path
     * @return String specifying any errors encountered during the process
     */
    private static String createDataFile(String dataPath) {
        String[] pathComponents = dataPath.split("/");
        int numOfComponents = pathComponents.length;
        String fileName = pathComponents[numOfComponents - 1];
        // beforeLastClash: Basically remove the filename and final '/'
        int beforeLastSlash = dataPath.length() - fileName.length() - 1;
        String parentDir = dataPath.substring(0, beforeLastSlash);
        Path parentPath = Paths.get(parentDir);
        try {
            Files.createDirectories(parentPath);
        } catch (IOException exception) {
            return StorageManager.FAILED_TO_CREATE_DATA_FILE_ERROR;
        }
        String NO_ERRORS_RESULT = "";
        return NO_ERRORS_RESULT;
    }

    private String getDataPath() {
        return this.dataPath;
    }

    /**
     * Reads the data file(if it exists) and re-generates the list of Tasks that
     *   Rica had been keeping track of previously.
     * Don't want Rica to have amnesia, do we?
     *
     * @return ArrayList containing all the Tasks remembered by Rica previously
     * @throws RicaStorageException If an empty path was given or the specified
     *   data file doesn't exist
     */
    public ArrayList<Task> getSavedTasks() throws RicaStorageException {
        File dataFile;
        Scanner dataReader;
        ArrayList<Task> savedTasks = new ArrayList<>();
        try {
            dataFile = new File(this.getDataPath());
            dataReader = new Scanner(dataFile);
            while (dataReader.hasNext()) {
                String objectData = dataReader.nextLine();
                Task deserializedTask = Task.deserializeObject(objectData);
                savedTasks.add(deserializedTask);
            }
        } catch (NullPointerException exception) {
            throw new RicaStorageException(StorageManager.INVALID_DATA_PATH_GIVEN_ERROR);
        } catch (FileNotFoundException exception) {
            String result = StorageManager.createDataFile(this.getDataPath());
            String errorMessage = StorageManager.DATA_FILE_NOT_FOUND_ERROR;
            if (!result.isBlank()) {
                errorMessage += System.lineSeparator() + result;
            }
            errorMessage += System.lineSeparator()
                    + "    ____________________________________________________________";
            throw new RicaStorageException(errorMessage);
        }
        return savedTasks;
    }

    /**
     * Serializes the given list of Tasks from Rica into lines of text that are
     *   written to our designated data file.
     *
     * @param tasks ArrayList of Tasks that Rica is currently keeping track of, and
     *   that she would like us to save for her
     * @throws RicaStorageException If Rica's data cannot be fully written to our
     *   designated data file, meaning that she'll be getting memory loss(oh dear!)
     */
    public void saveTasks(ArrayList<Task> tasks) throws RicaStorageException {
        FileWriter dataFile;
        try {
            dataFile = new FileWriter(this.getDataPath(), false);
            for (Task task : tasks) {
                String serializedData = task.serializeObject();
                dataFile.write(serializedData + System.lineSeparator());
            }
            dataFile.close();
        } catch (IOException exception) {
            throw new RicaStorageException();
        }
    }
}
