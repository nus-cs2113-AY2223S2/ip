package serialiser;

import task.ITaskController;

public interface ISerialiser {
    public void deserialiseFile(ITaskController taskController);
    public void serialiseFile(ITaskController taskController);
}
