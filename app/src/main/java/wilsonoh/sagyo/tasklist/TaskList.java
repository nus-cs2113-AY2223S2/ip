package wilsonoh.sagyo.tasklist;

import java.util.ArrayList;
import java.util.Iterator;

import wilsonoh.sagyo.exceptions.InvalidTaskException;
import wilsonoh.sagyo.storage.Storage;
import wilsonoh.sagyo.tasks.Task;

public class TaskList implements Iterable<Task> {

    private final ArrayList<Task> tasks = new ArrayList<>();

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void markTask(int index) {
        tasks.get(index).markDone();
    }

    public void unMarkTask(int index) {
        tasks.get(index).unMarkDone();
    }

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public void extendFromStorage(Storage storage) throws InvalidTaskException {
        tasks.addAll(storage.getTaskListFromJSON());
    }

    public String[] getTasksString() {
        String[] taskStrings = new String[tasks.size()];
        int idx = 0;
        for (Task task : tasks) {
            taskStrings[idx] = String.format("%d: %s", idx + 1, task);
            idx++;
        }
        return taskStrings;
    }
}
