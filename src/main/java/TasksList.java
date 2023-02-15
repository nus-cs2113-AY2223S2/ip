import java.util.ArrayList;

public class TasksList {
    private ArrayList<Task> tasks;

    public TasksList () {
        this.tasks = new ArrayList<>();
    }


    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getTasksCount() {
        return tasks.size();
    }

    public String toString() {
        int index = 1;
        StringBuilder output = new StringBuilder();
        for (Task task: tasks) {
                output.append(index).append(". ").append(task);
                // Don't add a break after the last line
                if (index != tasks.size()){
                    output.append(System.lineSeparator());
                }
                index++;
        }
        return output.toString();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task removeTask(int index) {
        Task taskToRemove = tasks.get(index);
        tasks.remove(index);
        return taskToRemove;
    }
}
