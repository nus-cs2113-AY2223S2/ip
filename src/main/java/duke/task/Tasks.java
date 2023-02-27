package duke.task;

import java.util.ArrayList;

public abstract class Tasks {
    private String item;
    private boolean isMarked;
    private static int numberOfTasks = 0;
    private static ArrayList<Tasks> taskList = new ArrayList<>();
    public Tasks(String item, boolean isMarked) {
        this.item = item;
        this.isMarked = isMarked;
        numberOfTasks++;
    }
    public static void addToList(Tasks task) {
        taskList.add(task);
    }
    public static void deleteFromList(int pos) {
        taskList.remove(pos);
        numberOfTasks--;
    }

    public static ArrayList<Tasks> getTaskList() {
        return taskList;
    }
    public static int getNumberOfTasks() {
        return numberOfTasks;
    }
    public void setMarked(boolean isMarked) {
        this.isMarked = isMarked;
    }
    public String toString() {
        String status;
        if (isMarked) {
            status = "[X] ";
        } else {
            status = "[ ] ";
        }
        return status + item;
    }
}
