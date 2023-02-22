package duke.filemanager;

import duke.exceptions.DukeException;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Handler to read / write tasks to file
 */
public class Storage {
    private String filePath;
    private TaskWriter taskWriter = new TaskWriter();
    private TaskLoader taskLoader = new TaskLoader();


    /**
     * Constructor to set the filePath where json file is stored
     *
     * @param filePath filePath of json file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Sets the arrayList of tasks from json file
     *
     * @return arrayList of tasks from previous session
     * @throws DukeException occurs when there is a file read error
     */
    public ArrayList<Task> setTasks() throws DukeException {
        return taskLoader.setClasses(filePath);
    }

    /**
     * Writes the current taskList to the json file
     *
     * @param taskList taskList containing all current tasks
     * @throws DukeException occurs when there is a file write error
     */
    public void writeTasks(ArrayList<Task> taskList) throws DukeException {
        taskWriter.writeToJson(taskList, filePath);
    }
}
