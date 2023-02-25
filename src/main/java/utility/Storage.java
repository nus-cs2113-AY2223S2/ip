package utility;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


import exception.CorruptedStoreException;
import task.Task;
import task.TaskList;
import task.Todo;
import task.Deadline;
import task.Event;

/**
 * The Storage class provides functionality to read from and write to a file containing a list of tasks.
 * * Tasks are represented by the Task class and its subclasses.
 */
public class Storage {
    final static String CONFIG_FILE_PATH = "src/config.properties";

    /**
     * Saves the specified TaskList to the file specified in the configuration file.
     *
     * @param tasks the TaskList to save to the file
     * @throws IOException if there is an I/O error
     */
    public static void saveToFile(TaskList tasks) throws IOException {

        FileInputStream propsInput = new FileInputStream(CONFIG_FILE_PATH);
        Properties prop = new Properties();
        prop.load(propsInput);

        String filePath = prop.getProperty("GENESIS_STORE_FILE_PATH");
        String dirPath = prop.getProperty("GENESIS_STORE_DIR_PATH");

        Files.createDirectories(Paths.get(dirPath));
        File file = new File(filePath);
        FileWriter fw = new FileWriter(file);

        for (Task task : tasks.getTasks()) {
            fw.write(task.toString() + '\n');
        }

        fw.flush();
        fw.close();
        propsInput.close();
    }

    /**
     * Loads a list of tasks from the file specified in the configuration file.
     *
     * @return an ArrayList of Task objects
     * @throws IOException               if there is an I/O error
     * @throws IndexOutOfBoundsException if the file is empty or has an invalid format
     * @throws CorruptedStoreException   if the file is corrupted and cannot be read
     */
    public static ArrayList<Task> loadFromFile() throws IOException, IndexOutOfBoundsException, CorruptedStoreException {

        ArrayList<Task> tasks = new ArrayList<>();

        FileInputStream propsInput = new FileInputStream(CONFIG_FILE_PATH);
        Properties prop = new Properties();
        prop.load(propsInput);

        String filePath = prop.getProperty("GENESIS_STORE_FILE_PATH");

        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        sc.useDelimiter("\n");

        while (sc.hasNext()) {
            String entry = sc.next();
            String[] contentArr = entry.split(" \\| ");
            switch (contentArr[0]) {
            case "T":
                Todo todo = new Todo(contentArr[2], Boolean.parseBoolean(contentArr[1]));
                tasks.add(todo);
                break;
            case "D":
                Deadline deadline = new Deadline(contentArr[2], contentArr[3], Boolean.parseBoolean(contentArr[1]));
                tasks.add(deadline);
                break;
            case "E":
                Event event = new Event(contentArr[2], contentArr[3], contentArr[4], Boolean.parseBoolean(contentArr[1]));
                tasks.add(event);
                break;
            default:
                throw new CorruptedStoreException();
            }
        }

        sc.close();
        propsInput.close();

        return tasks;
    }
}
