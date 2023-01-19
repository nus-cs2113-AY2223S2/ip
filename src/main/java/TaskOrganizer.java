import java.util.ArrayList;
public class TaskOrganizer implements Cloneable {
    private ArrayList<String> tasks;

    public TaskOrganizer() {
        tasks = new ArrayList<String>();
    }

    public void addTask(String toAdd) {
        tasks.add(toAdd);
    }

    public ArrayList<String> getTaskList() {
        return new ArrayList<String>(tasks);
    }
}
