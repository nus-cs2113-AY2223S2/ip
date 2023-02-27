package duke.task;
import java.util.ArrayList;
public class TaskList {
    private static int numberOfTasks = 0;
    private static ArrayList<Tasks> tasklist = new ArrayList<>();
    public static ArrayList<Tasks> getTaskList() {
        return tasklist;
    }
    public static int getNumberOfTasks() {
        return numberOfTasks;
    }
    public static void addToList(Tasks task) {
        tasklist.add(task);
        numberOfTasks++;
    }
    public static void deleteFromList(int pos) {
        tasklist.remove(pos);
        numberOfTasks--;
    }
}
