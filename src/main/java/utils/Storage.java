package utils;

import exceptions.FileLineParseException;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The tool manage reading and writing between the task list in the memory and the
 * local file in the disk.
 */
public class Storage {
    String filePath;
    public Storage(String filePath){
        this.filePath = filePath;
    }


    /**
     * Load the local file. See more details in dukeFileReader.readFileToTaskList()
     * @return An array list containing all the task.
     * @throws FileLineParseException
     * @throws FileNotFoundException
     */
    public ArrayList<Task> load() throws FileLineParseException, FileNotFoundException {
        DukeFileReader dukeFileReader = new DukeFileReader(filePath);
        ArrayList<Task> tasks = dukeFileReader.readFileToTaskList();

        return tasks;
    }

    /**
     * Add a new object to local file.
     * @param newObject The new object the user want to add.
     * @param ui The ui module to show exception messages.
     */
    public void addNewObjectToFile(Task newObject, Ui ui) {
        DukeFileWriter dukeFileWriter = new DukeFileWriter(filePath);
        try {
            dukeFileWriter.addNewObjectToFile(newObject);
        } catch (IOException e) {
            ui.showIOException();
        }
    }

    /**
     * Rewrite the whole task list in the memory to the disk.
     * @param tasks The whole task list.
     * @param ui The ui module to show exception messages.
     */
    public void rewrite(TaskList tasks, Ui ui){
        DukeFileWriter dukeFileWriter = new DukeFileWriter(filePath);
        try {
            dukeFileWriter.rewriteAllToFile(tasks);
        } catch (IOException e) {
            ui.showIOException();
        }
    }
}
