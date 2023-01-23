package Task;

import java.util.ArrayList;

public interface ITaskController {
	public ArrayList<Task> getTask() throws EmptyTaskList;
	public void addTask(String description);
}
