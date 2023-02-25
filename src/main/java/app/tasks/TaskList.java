package app.tasks;

import app.exceptions.DukeException;
import app.exceptions.InvalidTaskException;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        setTasks(tasks);
    }

    private void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public Task getTask(int index) throws DukeException {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskException();
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public int getTasksCount() {
        return tasks.size();
    }

    public Task deleteTask(int index) throws DukeException{
        Task taskToDelete = getTask(index - 1);
        return tasks.remove(index - 1);
    }

    public Task markTask(int index) throws DukeException {
        Task markedTask = getTask(index - 1);
        markedTask.setDone(true);
        return markedTask;
    }

    public Task unmarkTask(int index) throws DukeException {
        Task unmarkedTask = getTask(index - 1);
        unmarkedTask.setDone(false);
        return unmarkedTask;
    }
}
