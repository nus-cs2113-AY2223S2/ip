package duke.classes;

import java.util.ArrayList;

public class Tasklist {

    protected static ArrayList<Task> listOfTask;

    public Tasklist(ArrayList<Task> listOfTask) {
        this.listOfTask = listOfTask;
    }

    static void addTask(Task task) {
        listOfTask.add(task);
    }

    static void markTask(Task task) {
        task.isDone = true;
    }

    static void unmarkTask(Task task) {
        task.isDone = false;
    }
    static void printTasks() {
        int order = 1;
        for (int i = 0; i < listOfTask.size(); i++) {
            System.out.println(order + "." + listOfTask.get(i));
            order++;
        }
    }

    static void printFoundTasks(String keyWord) {
        int order = 1;
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < listOfTask.size(); i++) {
            Task taskFound = listOfTask.get(i);
            String taskInfo = taskFound.toString();
            if (taskInfo.contains(keyWord)) {
                System.out.println(order + "." + taskFound);
                order++;
            }
        }
    }
}
