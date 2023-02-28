package duke.task;
import java.util.ArrayList;
public class TaskList {
    private static int numberOfTasks = 0;
    //private ArrayList<Tasks> taskList;
    private static final ArrayList<Tasks> taskList = new ArrayList<>();

//    public TaskList(ArrayList<Tasks> taskList) {
//        this.taskList = taskList;
//    }

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
