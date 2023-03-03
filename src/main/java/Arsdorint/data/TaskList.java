package Arsdorint.data;

import Arsdorint.task.Task;

import java.util.ArrayList;

/* Represent the TaskList, and some functions to deal with messages */
public class TaskList {
    public TaskList() {
    }
    public static final int MAX_NUM_OF_TASKS = 100;
    public static ArrayList<Task> list = new ArrayList<Task>(MAX_NUM_OF_TASKS);
    public static String getAllMessage() {
        int count = TaskList.list.size();
        return "\t" + "Now you have " + count + " " + printTasksOrTask(count) + " in the list.";
    }

    public static String getMarkedMessage() {
        int count = (int) TaskList.list.stream().filter(i-> i.isDone).count();
        return "\t" + "Now you have " + count + " marked " + printTasksOrTask(count) + " in the list.";
    }

    public static String getUnmarkedMessage() {
        int count = (int) TaskList.list.stream().filter(i-> !i.isDone).count();
        return "\t" + "Now you have " + count + " unmarked " + printTasksOrTask(count) + " in the list.";
    }

    public static String printTasksOrTask(int num) {
        return ((num == 1) ? "task" : "tasks");
    }
}
