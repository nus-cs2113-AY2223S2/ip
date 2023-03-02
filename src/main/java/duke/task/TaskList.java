package duke.task;
import java.util.ArrayList;
public class TaskList {
    private static int numberOfTasks = 0;
    private static final ArrayList<Tasks> taskList = new ArrayList<>();
    public static ArrayList<Tasks> getTaskList() {
        return taskList;
    }

    public static int getNumberOfTasks() {
        return numberOfTasks;
    }
    public static void addToList(Tasks task) {
        taskList.add(task);
        numberOfTasks++;
    }
    public static void deleteFromList(int pos) {
        taskList.remove(pos);
        numberOfTasks--;
    }
}
