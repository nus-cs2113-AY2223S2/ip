import java.util.ArrayList;
public class TaskList {
    public TaskList(ArrayList<Task> userTasks) {
        this.userTasks = userTasks;
    }
    private ArrayList<Task> userTasks;

    public ArrayList<Task> getUserTasksArrayList() {
        return userTasks;
    }

    public Task getUserTask(int index) {
        return userTasks.get(index);
    }
    public void addUserTask(Task newTask) {
        userTasks.add(newTask);
    }

    public void removeUserTask(int index) {
        userTasks.remove(index);
    }

    public int getNumberOfUserTasks() {
        return userTasks.size();
    }

    public void setTaskDone(int index) {
        userTasks.get(index).setisDone(true);
    }

    public void setTaskNotDone(int index) {
        userTasks.get(index).setisDone(false);
    }
}
