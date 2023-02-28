package psyduck.tasklist;

import java.util.ArrayList;
import psyduck.task.Task;
import psyduck.ui.Ui;

public class TaskList {
    private static final ArrayList<Task> tasks = new ArrayList<>();

    private static int taskCount = 0;

    public static int getTaskCount() {
        return taskCount;
    }


    public static Task getTask(int taskNum) {
        return tasks.get(taskNum - 1); //array is 0-indexed, taskNum is 1-indexed
    }

    public static Task getNewestTask() {
        return tasks.get(taskCount - 1); //array is 0-indexed, taskNum is 1-indexed
    }

    public void addTask(Task task) {
        tasks.add(task);
        taskCount++;
    }


    public void removeTask(int taskNum) {
        tasks.remove(taskNum - 1);
        taskCount--;

    }

    public void listTasks() {
        Ui.linePrint();
        if (taskCount == 0) { //list is empty
            System.out.println("List is empty.");
        } else {
            for (int i = 0; i < taskCount; i++) {
                System.out.print(i + 1 + ".");
                System.out.println(tasks.get(i));
            }
        }
        Ui.linePrint();
    }
}
