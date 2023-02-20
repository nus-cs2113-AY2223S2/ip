package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    public ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> database) {
        tasks = database;
    }

    public void deleteTaskFromTaskList(int deleteIndex) {
        tasks.remove(deleteIndex);
    }

    public void addTaskToTaskList(Task task) {
        tasks.add(task);
    }

    public Task getTaskFromIndex(int index) {
        return tasks.get(index);
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public void printTasksByKeyword(String keyword) {
        for (int i = 0; i < tasks.size(); ++i) {
            if (tasks.get(i).getTaskString().contains(keyword)) {
                System.out.println(tasks.get(i));
            }
        }
    }

}
