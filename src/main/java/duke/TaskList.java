package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public void addTask (Task task) {
        tasks.add(task);
    }

    public Task getTask (int index) throws IncorrectIndexException {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        } else {
            throw new IncorrectIndexException();
        }
    }

    public void removeTask (int index) throws IncorrectIndexException {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        } else {
            throw new IncorrectIndexException();
        }
    }

    public int length () {
        return tasks.size();
    }
}
