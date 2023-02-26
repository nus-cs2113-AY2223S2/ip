package duke.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tasklist {
    private final ArrayList<Task> taskList;

    public Tasklist() {
        this.taskList = new ArrayList<>();
    }

    public Tasklist(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }


    public void deleteTask(int index) {
        taskList.remove(index);
    }


    public void markTask(int index) {
        Task task = taskList.get(index);
        task.markAsDone();
    }


    public void unmarkTask(int index) {
        Task task = taskList.get(index);
        task.markAsUndone();
    }


    public int getNumberOfTasks() {
        return taskList.size();
    }


    public String[] listOfTasks() {
        String[] tasks = new String[taskList.size()];
        for (int i = 0; i < taskList.size(); i++) {
            tasks[i] = String.format("%d. %s", i + 1, taskList.get(i).toString());
        }
        return tasks;
    }


    public Task getTask(int index) {
        return taskList.get(index);
    }


    public String[] findTasks(String keyword) {
        ArrayList<String> tasks = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            List<String> taskDetails = Arrays.asList(task.description.split("\\s+"));
            if (taskDetails.contains(keyword)) {
                tasks.add(String.format("%d. %s", i + 1, taskList.get(i).toString()));
            }
        }
        return tasks.toArray(new String[0]);
    }


    public String[] saveToTextFile() {
        String[] tasks = new String[taskList.size()];
        for (int i = 0; i < taskList.size(); i++) {
            tasks[i] = taskList.get(i).saveText();
        }
        return tasks;
    }
}
