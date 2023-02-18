package serialiser;

import duke.DukeException;
import task.ITaskController;

public interface ISerialiser {
    /**
     * Loads the data file needed for duke. If not exist, then create directory and data file
     * @param taskController
     * @throws DukeException
     */
    public void loadDataFile(ITaskController taskController) throws DukeException;
    /**
     * Assumes that the directory and file exists. Saves the data of runtime into text file
     * @param taskController
     */
    public void saveDataFile(ITaskController taskController);
}
