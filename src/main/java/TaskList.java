import duke.task.Task;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    public ArrayList<Task> getTaskList() {
        return taskList;
    }
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public int sizeOfTaskList() {
        return taskList.size();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(int index) {
        try {
            taskList.remove(index);
        } catch (IndexOutOfBoundsException exception) {
            Messages.invalidTaskMessage();
        }
    }

    public void markAsDone(int index) {
        try {
            Task curTask = taskList.get(index);
            curTask.setAsDone();
        } catch (IndexOutOfBoundsException exception) {
            Messages.invalidTaskMessage();
        }
    }

    public void markAsUnDone(int index) {
        try {
            Task curTask = taskList.get(index);
            curTask.setAsUndone();
        } catch (IndexOutOfBoundsException exception) {
            Messages.invalidTaskMessage();
        }
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }
}
