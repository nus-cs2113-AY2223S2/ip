package chronos.savehandler;

import chronos.tasktype.Task;

import java.nio.file.*;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * The Storage class provides methods to read and write tasks to a file.
 * The file is stored as a plain text file. FOR CLARIFICATION: Stash is what is compiled during runtime,
 * Storage is saved in the hard disk
 */
public class Storage {
    private static final Logger LOGGER = Logger.getLogger(Storage.class.getName());
    private static final String FILE = "./taskList.txt";

    /**
     * Writes the given content to a file located at the specified path.
     * @param path the path of the file to write to
     * @param content the content to write to the file
     */
    private static void writeToFile(String path, String content) {
        try {
            Path absolutePath = Paths.get(path);
            Files.createDirectories(absolutePath.getParent());
            Files.writeString(absolutePath, content, StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException error) {
            LOGGER.warning("Failed to write to file: " + error.getMessage());
        }
    }

    /**
     * Reads the contents of the file located at the specified path.
     * @param path the path of the file to read from
     * @return the contents of the file as a string
     */
    private static String readFromFile(String path) {
        try {
            return Files.readString(Paths.get(path));
        } catch (IOException error){
            LOGGER.warning("Failed to read from file: " + error.getMessage());
            return "";
        }
    }
    /**
     * Reads the saved tasks from the file and returns them as an ArrayList.
     * @return an ArrayList of saved tasks
     */
    public static ArrayList<Task> readSavedTasks(){
        String savedTask = readFromFile(FILE);
        return savedTask.isEmpty() ? new ArrayList<>() :
                Arrays.stream(savedTask.split("\n"))
                        .map(Task::new)
                        .collect(Collectors.toCollection(ArrayList::new));
    }
    /**
     * Saves the given tasks to the file.
     * @param tasks the tasks to save
     */
    public static void saveTasks(ArrayList<Task> tasks) {
        StringBuilder savedTask = new StringBuilder();
        for (Task task : tasks) {
            savedTask.append(task.toString()).append("\n");
        }
        writeToFile(FILE, savedTask.toString());
    }

}
