package Arsdorint.data;

import Arsdorint.task.Task;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static Arsdorint.MessageList.*;

/**
 * Represent the middleman class between the main and the encoding, decoding
 */
public class StorageClass {
    public StorageClass() {
        path = Paths.get(STORAGE_FILE_NAME);
    }
    public static final String STORAGE_DIRECTORY = "./storage";
    public static final String STORAGE_FILE_NAME = "./storage/arsdorintTask.txt";
    private final Path path;
    private static final String ERROR_SAVING_MESSAGE = "Error saving to ";
    private static final String ERROR_LOADING_MESSAGE = "Error loading to ";
    private static final String ERROR_PARSING_MESSAGE = "Error parsing ";

    /**
     * Method to save the file
     */
    public void save(TaskList taskList) throws StorageException {
        try {
            List<String> encodedTaskList = TaskListEncoder.encodeList(taskList);
            Files.write(path, encodedTaskList);
        } catch (IOException err) {
            File newFile = new File(STORAGE_DIRECTORY);
            newFile.mkdir();
            save(taskList);
            throw new StorageException(ERROR_SAVING_MESSAGE + path + MESSAGE_NEW_FILE);
        }
    }
    public TaskList load() throws StorageException {
        TaskList list = new TaskList();
        try {
            ArrayList<Task> decodedList = TaskListDecoder.decodeFile(STORAGE_FILE_NAME);
            list.data = decodedList;
            return list;
        } catch (IOException err) {
            throw new StorageException(ERROR_LOADING_MESSAGE + path);
        } catch (TaskListDecoder.DecodeException err) {
            throw new StorageException(ERROR_PARSING_MESSAGE + path);
        }
    }

    public static class StorageException extends Exception {
        public StorageException(String message) {
            super(message);
        }
    }

}
