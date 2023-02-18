package serialiser;

import duke.DukeException;
import task.ITaskController;

public interface ISerialiser {
    public void loadDataFile(ITaskController taskController) throws DukeException;
    public void saveDataFile(ITaskController taskController);
}
