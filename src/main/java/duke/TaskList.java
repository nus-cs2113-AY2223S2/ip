package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> database) {
        tasks = database;
    }

    public void addToTaskList(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int indexToRemove) {
        tasks.remove(indexToRemove);
    }

    public int getTaskListSize() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

}
