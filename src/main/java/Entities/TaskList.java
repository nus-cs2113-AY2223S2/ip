package Entities;

import java.util.ArrayList;

import Exceptions.DukeException;

import java.lang.IndexOutOfBoundsException;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        setTasks(tasks);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int taskIndex) throws DukeException {
        getTask(taskIndex);             // Checks if taskIndex is valid
        return tasks.remove(taskIndex);
    }

    public Task markTask(int taskIndex) throws DukeException {
        Task t = getTask(taskIndex);
        t.setIsDone(true);
        return t;
    }

    public Task unmarkTask(int taskIndex) throws DukeException {
        Task t = getTask(taskIndex);
        t.setIsDone(false);
        return t;
    }
    
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getTasksCount() {
        return tasks.size();
    }

    public Task getTask(int taskIndex) throws DukeException {
        try {
            return getTasks().get(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Uh oh, the index you have inputted is out of range!");
        }
    }
}
