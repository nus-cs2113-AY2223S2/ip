package duke.task;

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

    public String deleteTask(int i) {
        String taskString = tasks.get(i).toString();
        tasks.remove(i);
        return taskString;
    }

    public String toString() {
        StringJoiner taskListString = new StringJoiner(System.lineSeparator());
        for (int i = 0; i < size(); i++) {
            taskListString.add((i + 1) + "." + getTask(i).toString());
        }
        return taskListString.toString();
    }

    /**
     * Filters the task list before converting it into its string representation.
     *
     * @param filter The string that task descriptions should contain to be filtered.
     * @return A string representation of the filtered task list.
     */
    public String toString(String filter) {
        StringJoiner taskListString = new StringJoiner(System.lineSeparator());
        for (int i = 0, index = 0; i < size(); i++) {
            if (getTask(i).containsFilter(filter)) {
                taskListString.add((++index) + "." + getTask(i).toString());
            }
        }
        return taskListString.toString();
    }
}
