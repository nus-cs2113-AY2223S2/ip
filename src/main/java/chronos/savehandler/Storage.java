package chronos.savehandler;
//FOR CLARIFICATION: Stash is what is compiled during runtime, Storage is saved in the hard disk
import chronos.tasktype.Task;

import java.nio.file.*;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.Arrays;
import java.util.stream.Collectors;



public class Storage {
    private static final Logger LOGGER = Logger.getLogger(Storage.class.getName());
    private static final String FILE = "src/main/java/chronos/taskList.txt";

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
    private static String readFromFile(String path) {
        try {
            return Files.readString(Paths.get(path));
        } catch (IOException error){
            LOGGER.warning("Failed to read from file: " + error.getMessage());
            return "";
        }
    }

    public static ArrayList<Task> readSavedTasks(){
        String savedTask = readFromFile(FILE);
        return savedTask.isEmpty() ? new ArrayList<>() :
                Arrays.stream(savedTask.split("\n"))
                        .map(Task::new)
                        .collect(Collectors.toCollection(ArrayList::new));
    }
    public static void saveTasks(ArrayList<Task> tasks) {
        StringBuilder savedTask = new StringBuilder();
        for (Task task : tasks) {
            savedTask.append(task.toString()).append("\n");
        }
        writeToFile(FILE, savedTask.toString());
    }

}
