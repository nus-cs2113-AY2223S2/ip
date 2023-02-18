package Entities;

import java.util.ArrayList;

import EntityUtils.DateParser;
import Exceptions.DukeException;

import java.lang.IndexOutOfBoundsException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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
        getTask(taskIndex);                 // Throws an exception if the taskIndex is not valid 
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

    public TaskList getFilteredTasks(String taskSubstring) {
        TaskList filteredTasks = new TaskList();
        for (Task t : getTasks()) {
            if (!t.getTaskDescription().toLowerCase().contains(taskSubstring.toLowerCase())) {
                continue;
            }
            filteredTasks.addTask(t);
        }
        return filteredTasks;
    }
    
    public TaskList getUpcomingTasks() {
        TaskList upcomingTasks = new TaskList();
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime deadline;
        for (Task t : getTasks()) {
            if (t instanceof Deadline) {
                deadline = DateParser.stringToDate(((Deadline) t).getEndDate());
            } else if (t instanceof Event) {
                deadline = DateParser.stringToDate(((Event) t).getEndDate());
            } else {
                continue;
            }
            
            // If deadline is not within 3 days from now
            if ((deadline.isBefore(currentDate) || deadline.isAfter(currentDate.plus(3, ChronoUnit.DAYS)))) {
                continue;
            }

            upcomingTasks.addTask(t);
        }
        return upcomingTasks;
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
