import java.util.ArrayList;
public class Tasks {
    private String item;
    private int id;
    private boolean isMarked;
    private static int numberOfTasks = 0;
    private static ArrayList<Tasks> taskList = new ArrayList<>();


    public Tasks(String item, boolean isMarked) {
        this.item = item;
        this.isMarked = isMarked;
        numberOfTasks++;
        this.id = numberOfTasks - 1;
    }

    public static void addToList(Tasks task){

        taskList.add(task);
    }

    public static ArrayList<Tasks> getTaskList() {

        return taskList;
    }

    public Tasks getTask(int taskNum) {

        return taskList.get(taskNum - 1); //zero-based
    }

    public static int getNumberOfTasks() {

        return numberOfTasks;
    }

    public int getID() {

        return id;
    }

    public String getItem() {

        return item;
    }

    public void setItem(String item) {

        this.item = item;
    }

    public boolean isMarked() {

        return isMarked;
    }

    public void setMarked(boolean isMarked) {

        this.isMarked = isMarked;
    }
}
