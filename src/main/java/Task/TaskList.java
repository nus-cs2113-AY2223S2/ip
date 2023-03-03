package Task;

import java.io.IOException;
import java.util.ArrayList;
import Storage.DukeStorage;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(DukeStorage storage) throws IOException{
        tasks = storage.loadTaskList();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public static String printTasksList(ArrayList<Task> tasks) {
        String tasksList = new String();
        int count = 1;
        for (Task i : tasks) {
            tasksList += count + ". " + i.toString();
            if (count < Task.numberOfTasks) {
                tasksList += System.lineSeparator();
            }
            count++;
        }
        return tasksList;
    }
}