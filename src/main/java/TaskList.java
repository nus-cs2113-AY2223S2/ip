import java.util.ArrayList;
import java.util.Arrays;
public class TaskList {
    ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }
    public TaskList(Task[] inputTasks) {
        this();
        tasks.addAll(Arrays.asList(inputTasks));
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int i) {
        return tasks.get(i);
    }
    public void remove(int i) {
        tasks.remove(i);
    }

    public int size() {
        return tasks.size();
    }

    public Task[] asList() {
        return tasks.toArray(new Task[0]);
    }

    public ArrayList<Task> filterByKeyword(String keyword) {
        ArrayList<Task> filteredTasks = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i += 1) {
            String taskName = tasks.get(i).getDescription();
            if (taskName.contains(keyword)) {
                filteredTasks.add(tasks.get(i));
            }
        }
        return filteredTasks;
    }

}
