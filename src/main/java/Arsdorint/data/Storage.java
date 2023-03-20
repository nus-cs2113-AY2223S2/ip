package Arsdorint.data;

import Arsdorint.command.ArsdorintFileException;
import Arsdorint.command.StorageException;
import Arsdorint.parser.TaskParser;
import Arsdorint.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Arsdorint.MessageList.*;
import static Arsdorint.MessageList.MESSAGE_WRONG_FILE;

public class Storage {
    public Storage() {
        path = Paths.get(STORAGE_FILE_NAME);
    }
    public static final String STORAGE_DIRECTORY = "./storage";
    public static final String STORAGE_FILE_NAME = "./storage/arsdorintTask.txt";
    private static final Path path = null;
    private static final String ERROR_SAVING_MESSAGE = "Error saving to ";
    private static final String ERROR_LOADING_MESSAGE = "Error loading to ";
    private static final String ERROR_PARSING_MESSAGE = "Error parsing ";
    public static String[] save(TaskList taskList) throws StorageException {
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
        } catch (DecodeException err) {
            throw new StorageException(ERROR_PARSING_MESSAGE + path);
        }
    }

    public static class StorageException extends Exception {
        public StorageException(String message) {
            super(message);
        }
    }

}
