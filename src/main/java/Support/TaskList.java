package Support;

import java.util.ArrayList;

public class TaskList {
    // This class is related to the class Task, that stores all the input tasks in it.
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int taskslength = 0;

    public static void addTask(Task newTask) {
        tasks.add(newTask);
        taskslength++;
    }

    public static void deleteTask(int index) {
        tasks.remove(index);
        taskslength--;
    }

    public static Task getTask(int index) {
        return tasks.get(index);
    }

    public static int getSize() {
        return taskslength;
    }


}