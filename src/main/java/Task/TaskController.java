package Task;

import java.util.ArrayList;


public class TaskController implements ITaskController{
    ArrayList<Task> list = new ArrayList<Task>();
    public void addTask(String description) {
        list.add(new Task(description));
    }
    public ArrayList<Task> getTask() throws EmptyTaskList {
        if (list.isEmpty()) {
            throw new EmptyTaskList("*** Empty List");
        }
        return list;
    }
}
