package Handlers;

import Tasks.Task;
import java.util.ArrayList;

public class TaskManager {
    private static ArrayList<Task> taskList = new ArrayList<>();

    public static ArrayList<Task> getTaskList() {
        return taskList;
    }

    public static void addTask(Task t) {
        taskList.add(t);
    }

    public static void deleteTask (int index) {
        taskList.remove(index - 1);
        System.out.println("Noted. I've removed this task:\n" + taskList.get(index - 1).describeTask() + "\n");
    }

    public static int getTaskCount() {
        return taskList.size();
    }

    public static void listTask() {
        if (taskList.isEmpty()) {
            System.out.println("You have no tasks in your list yet!");
            return;
        }

        int existingTaskCount = 1;
        for (Task item : taskList) {
            System.out.println(existingTaskCount + ". " + item.describeTask());
            existingTaskCount++;
        }

        System.out.println("\nYou have " + getTaskCount() + " tasks in the list.\n");
    }

    public static void markTask(int index) {
        if (index > 0 && index <= getTaskCount()) {
            ArrayList<Task> list = getTaskList();
            Task task = list.get(index - 1);
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:\n" + task.describeTask() + "\n");
        }
    }

    public static void unmarkTask(int index) {
        if (index > 0 && index <= getTaskCount()) {
            ArrayList<Task> list = getTaskList();
            Task task = list.get(index - 1);
            task.unmarkAsDone();
            System.out.println("OK, Ive marked this task as not done yet:\n" + task.describeTask() + "\n");
        }
    }

}
