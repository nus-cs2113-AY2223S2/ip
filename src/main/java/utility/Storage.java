package utility;


import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
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
 * The Storage class provides methods for saving and loading a list of tasks to and from a file.
 */
public class Storage {

    final static String GENESIS_STORE_DIR_PATH = "./store";
    final static String GENESIS_STORE_FILE_PATH = "./store/genesis.txt";


    /**
     * Saves the given list of tasks to the store file.
     *
     * @param tasks the list of tasks to be saved
     * @throws IOException if an I/O error occurs while writing to the store file
     */
    public static void saveToFile(TaskList tasks) throws IOException {

        Files.createDirectories(Paths.get(GENESIS_STORE_DIR_PATH));
        File file = new File(GENESIS_STORE_FILE_PATH);
        FileWriter fw = new FileWriter(file);

        for (Task task : tasks.getTasks()) {
            fw.write(task.toString() + '\n');
        }

        fw.flush();
        fw.close();
    }

    /**
     * Loads the list of tasks from the store file.
     *
     * @return the list of tasks loaded from the store file
     * @throws IOException               if an I/O error occurs while reading from the store file
     * @throws IndexOutOfBoundsException if the content of the store file is not formatted correctly
     * @throws CorruptedStoreException   if the store file is corrupted and cannot be loaded
     */
    public static ArrayList<Task> loadFromFile() throws IOException, IndexOutOfBoundsException, CorruptedStoreException {

        ArrayList<Task> tasks = new ArrayList<>();

        File file = new File(GENESIS_STORE_FILE_PATH);
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

        return tasks;
    }
}
