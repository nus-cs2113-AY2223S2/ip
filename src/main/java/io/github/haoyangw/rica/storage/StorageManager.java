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
