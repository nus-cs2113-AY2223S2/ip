package duke.storage;

import duke.tasklist.Task;
import duke.tasklist.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a txt file storing information.
 */
public class FileReading {
    private static final String filePath = "data/Duke.txt";

    /**
     * The list of tasks.
     */
    private static ArrayList<Task> tasks;

    /**
     * Creates a new txt file using the specified file path.
     * @throws IOException if stream to file cannot be written.
     */
    public static void createFile() throws IOException {
        File newFolder = new File("data");
        if (!newFolder.exists()) {
            newFolder.createNewFile();
        } else {
            newFolder.delete();
            newFolder.createNewFile();
        }
        newFolder.mkdirs();
        File file = new File("data/duke.txt");
        if (!file.exists()) {
            file.createNewFile();
        } else {
            file.delete();
            file.createNewFile();
        }
    }

    /**
     * Gets content of the txt file and putting them into the list.
     * @throws FileNotFoundException if file does not exist.
     */
    public static void getFileContents() throws FileNotFoundException {
        File f = new File(filePath);
        if (!f.exists()) {
            throw new FileNotFoundException();
        }
        Scanner s = new Scanner(f);

        try {
            List<String> taskList = Files.readAllLines(Path.of(filePath));
            TaskList.readTask(taskList);
        } catch (NullPointerException e) {
            System.out.println("Can't remember what was saved :(");
        } catch (IOException e) {
            System.out.println("IOException :(");
            //throw new RuntimeException(e);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There is something wrong.");
        }
    }

    /**
     * Delete all content from the txt file and create a new file.
     * @param filePath The path of the txt file.
     * @throws IOException if stream to file cannot be written to.
     */
    public static void deleteFileContents(String filePath) throws IOException {
        File f = new File(filePath);
        try {
            f.delete();
            createFile();
        } catch (IOException e) {
            System.out.println("An error has occurred :( ");
        }
    }

    /**
     * Writes to the txt file the list of tasks.
     * @throws IOException if stream to file cannot be written to.
     */
    public static void writeToFile() throws IOException {
        FileWriter fw = new FileWriter(filePath);
        deleteFileContents(filePath);
        tasks = TaskList.getTasks();
        for (Task t : tasks) {
            fw.write(t.toString() + "\n");
        }
        fw.close();
    }
}
