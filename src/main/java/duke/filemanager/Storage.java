package duke.filemanager;

import duke.exceptions.DukeException;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Handler to read / write tasks to file.
 */
public class Storage {
    private String filePath;
    private TaskWriter taskWriter = new TaskWriter();
    private TaskLoader taskLoader = new TaskLoader();


    /**
     * Constructs the Storage handler and sets the filepath where json file is stored.
     *
     * @param filePath File Path of json file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Sets the arrayList of tasks from json file.
     *
     * @return ArrayList of tasks from previous session.
     * @throws DukeException Occurs when there is a file read error.
     */
    public ArrayList<Task> setTasks() throws DukeException {
        return taskLoader.setClasses(filePath);
    }

    /**
     * Writes the current taskList to the json file.
     *
     * @param taskList TaskList containing all current tasks.
     * @throws DukeException Occurs when there is a file write error.
     */
    public void writeTasks(ArrayList<Task> taskList) throws DukeException {
        taskWriter.writeToJson(taskList, filePath);
    }
}
