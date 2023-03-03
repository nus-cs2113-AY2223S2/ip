package duke.storage;

import duke.tasklist.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a txt file storing information.
 */
public class FileReading {
    private static final String filePath = "data/duke.txt";

    /**
     * Creates a new txt file using the specified file path.
     * @throws IOException if stream to file cannot be written.
     */
    public static void createFile() throws IOException {
        File newFolder = new File(System.getProperty("user.dir"),"data");
        if (!newFolder.exists()) {
            newFolder.mkdirs();
        } else {
            newFolder.delete();
            newFolder.mkdirs();
        }

        File newFile = new File(System.getProperty("user.dir"),"data/duke.txt");
        if (!newFile.exists()) {
            newFile.createNewFile();
        } else {
            newFile.delete();
            newFile.createNewFile();
        }
    }

    /**
     * Gets content of the txt file and putting them into the list.
     * @throws FileNotFoundException if file does not exist.
     */
    public static void getFileContents() throws FileNotFoundException {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                createFile();
            } catch (IOException e) {
                Ui.printMessage(Ui.CommandType.IOEXCEPTION);
                throw new RuntimeException(e);
            }
            throw new FileNotFoundException();
        }

        try {
            List<String> taskList = Files.readAllLines(Path.of(filePath));
            TaskList.readTask(taskList);
        } catch (NullPointerException e) {
            Ui.printMessage(Ui.CommandType.NULLPOINTER);
        } catch (IOException e) {
            Ui.printMessage(Ui.CommandType.IOEXCEPTION);
        } catch (IndexOutOfBoundsException e) {
            Ui.printMessage(Ui.CommandType.INDEXOUTOFBOUNDS);
        }
    }

    /**
     * Delete all content from the txt file and create a new file.
     * @param filePath The path of the txt file.
     */
    public static void deleteFileContents(String filePath) {
        File file = new File(filePath);
        try {
            file.delete();
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
        FileWriter fileWriter = new FileWriter(filePath);
        deleteFileContents(filePath);

        ArrayList<Task> tasks = TaskList.getTasks();
        for (Task task : tasks) {
            fileWriter.write(task.toString() + "\n");
        }
        fileWriter.close();
    }
}
