package duke.tasklist;

import duke.tasklist.task.Task;
import duke.tasklist.exception.TaskNotFoundException;
import duke.tasklist.exception.DuplicateTaskException;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.util.HashSet;

public class TaskList implements Iterable<Task> {
    private final List<Task> taskList = new ArrayList<>();

    public TaskList() {}

    public TaskList(Task... tasks) throws DuplicateTaskException {
        Set<Task> appeared = new HashSet<>();
        for (Task task : tasks) {
            if (appeared.contains(task)) {
                throw new DuplicateTaskException();
            } else {
                taskList.add(task);
                appeared.add(task);
            }
        }
    }

    public TaskList(Collection<Task> tasks) throws DuplicateTaskException {
        Set<Task> appeared = new HashSet<>();
        for (Task task : tasks) {
            if (appeared.contains(task)) {
                throw new DuplicateTaskException();
            } else {
                taskList.add(task);
                appeared.add(task);
            }
        }
    }

    public TaskList(TaskList other) {
        taskList.addAll(other.taskList);
    }

    public int size() {
        return taskList.size();
    }

    public List<Task> immutableList() {
        return Collections.unmodifiableList(taskList);
    }

    public boolean containsTask(Task toCheck) {
        for (Task task : taskList) {
            if (toCheck.equals(task)) {
                return true;
            }
        }
        return false;
    }

    public void addTask(Task toAdd) throws DuplicateTaskException {
        if (containsTask(toAdd)) {
            throw new DuplicateTaskException();
        }
        taskList.add(toAdd);
    }

    public void deleteTask(Task toDelete) throws TaskNotFoundException {
        final boolean deleted = taskList.remove(toDelete);
        if (!deleted) {
            throw new TaskNotFoundException();
        }
    }

    public void clear() {
        taskList.clear();
    }

    @Override
    public Iterator<Task> iterator() {
        return taskList.iterator();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else {
            return obj instanceof TaskList
                    && ((TaskList) obj).taskList.equals(this.taskList);
        }
    }
}
