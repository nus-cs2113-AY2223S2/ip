package Duke;

import static Duke.string.StringToTaskList.stringToTaskList;
import static Duke.string.TaskListToString.taskListToString;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Duke.task.DukeTask;
import Duke.task.DukeTaskList;

/**
 * Represents a storage object that handles the loading and saving of tasks.
 */
public class DukeStorage {

    private File textFile;

    /**
     * Creates a new DukeStorage object.
     *
     * @param filePath The path of the file to be loaded.
     */
    public DukeStorage(String filePath) {
        textFile = new File(filePath);

        try {
            Boolean isFileCreated = textFile.createNewFile();
            System.out.println((!isFileCreated) ? "Loading file..." : "Creating file...");
        } catch (Exception e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    /**
     * Saves the tasks to the file.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void save(DukeTaskList tasks) {
        String textToWrite = taskListToString(tasks.tasksList);

        dukeWrite(textToWrite);
    }

    /**
     * Writes the text to the file.
     *
     * @param textToWrite The text to be written to the file.
     */
    private void dukeWrite(String textToWrite) {
        try {
            FileWriter dukeWriter = new FileWriter(textFile);
            dukeWriter.write(textToWrite);
            dukeWriter.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Loads the tasks from the file.
     *
     * @param ui The user interface object.
     * @return The list of tasks loaded from the file.
     * @throws DukeException If there is an error loading the file.
     */
    public ArrayList<DukeTask> load(DukeUi ui) throws DukeException {
        String savedDataString = ui.readFromFile(textFile);
        ArrayList<DukeTask> savedData = stringToTaskList(savedDataString);
        return savedData;
    }

}
