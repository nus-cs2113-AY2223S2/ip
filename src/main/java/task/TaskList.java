package task;

import java.util.ArrayList;
import java.util.StringJoiner;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public int size() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public String addTask(Task task) {
        tasks.add(task);
        return task.toString();
    }

    public String setDone(int i, boolean isDone) {
        tasks.get(i).setDone(isDone);
        return tasks.get(i).toString();
    }

    public String toString() {
        StringJoiner taskListString = new StringJoiner(System.lineSeparator());
        for (int i = 0; i < size(); i++) {
            taskListString.add((i + 1) + "." + getTask(i).toString());
        }
        return taskListString.toString();
    }
}
