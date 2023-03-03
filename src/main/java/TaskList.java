import tasktypes.Task;

import java.util.ArrayList;

public class TaskList {
    private static final ArrayList<Task> TaskList = new ArrayList<>(10);
    private static int NumTasks = 0;
    public static ArrayList<Task> getList() {
        return TaskList;
    }
    public static void addItem (Task newTask) {
        TaskList.add(newTask);
        NumTasks += 1;
        System.out.println("Okay! I've added: [" + newTask.getTypeIcon() +"] " + newTask.getDescription());
    }
    public static int getNumItems() {
        return NumTasks;
    }
    public static void viewList () {
        for (int i = 0; i < TaskList.size(); ++i) {
            System.out.print(i+1 + ". ");
            System.out.println(TaskList.get(i).getTask());
        }
    }
    public static void markDone (int index) {
        if (TaskList.get(index).getStatusIcon().equals(" ")) {
            TaskList.get(index).markDone();
        }
    }
    public static void markNotDone (int index) {
        if (TaskList.get(index).getStatusIcon().equals("X")) {
            TaskList.get(index).markNotDone();
        }
    }
    public static Task getItem (int index) {
        return TaskList.get(index);
    }
    public static void printItem (int index) {
        System.out.print(index+1 + ". ");
        System.out.println(TaskList.get(index).getTask());
    }

    public static void deleteTask(int index) {
        TaskList.remove(index);
        NumTasks -= 1;
    }


}
