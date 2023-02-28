package duke;

import duke.task.Task;
import java.util.Iterator;
import java.util.ArrayList;
public class TaskList implements Iterable<Task> {
    private static ArrayList<Task>tasks = new ArrayList<>();
    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }
    public void addTask(Task task) {
        tasks.add(task);
    }
    public Task removeTask(int taskNumber) {
        return tasks.remove(taskNumber - 1);
    }

    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber - 1);
    }
    public int getSize() {
        return tasks.size();
    }

    public ArrayList<Task> findTasks (String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<Task>();

        for (Task task : tasks) {
            if(task.isFound(keyword)) {
                foundTasks.add(task);
            }
        }

        return foundTasks;
    }

}
