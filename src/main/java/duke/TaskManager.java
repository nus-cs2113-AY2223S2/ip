package duke;

import java.util.*;

/**
 * This class represents a task manager that stores and manages tasks.
 */
public class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Finds a task in the list of tasks that contains the given string.
     * If a matching task is found, its details are printed.
     * If no matching task is found, a message is printed.
     * 
     * @param s The string to search for in the task names.
     */
    public void findItem(String s) {
        boolean found = false;
        if (tasks.size() == 0) {
            System.out.println("The list is currently empty!");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).find(s)) {
                System.out.println("Item found: " + (i + 1) + "." + tasks.get(i).toString());
                found = true;
            }
        }
        if (found == false) {
            System.out.println("Sorry there is no match for your search !");
        }
    }

    /**
     * Adds a task with the given name and completion status to the list of tasks.
     * 
     * @param name   The name of the task.
     * @param isDone The completion status of the task.
     */
    public void addTask(String name, boolean isDone) {
        tasks.add(new Task(name, isDone));
    }

    /**
     * Adds a deadline with the given name, completion status, and deadline string
     * to the list of tasks.
     * 
     * @param name     The name of the deadline.
     * @param deadline The deadline string.
     * @param isDone   The completion status of the deadline.
     */
    public void addDeadline(String name, String deadline, boolean isDone) {
        tasks.add(new Deadline(name, isDone, deadline));
    }

    /**
     * Adds an event with the given name, start time, finish time, and completion
     * status to the list of tasks.
     * 
     * @param eventName  The name of the event.
     * @param startTime  The start time string.
     * @param finishTime The finish time string.
     * @param isDone     The completion status of the event.
     */
    public void addEvent(String eventName, String startTime, String finishTime, boolean isDone) {
        tasks.add(new Event(eventName, isDone, startTime, finishTime));
    }

    /**
     * Marks the task with the given ID as done.
     * 
     * @param id The ID of the task to mark as done.
     */
    public void markTask(int id) {
        tasks.get(id).setIsDone(true);
        System.out.println("This item has been marked as done!");
        System.out.println("[X] " + tasks.get(id).getName());
    }

    /**
     * Unmarks the task with the given ID as done.
     * 
     * @param id The ID of the task to unmark as done.
     */
    public void unmarkTask(int id) {
        tasks.get(id).setIsDone(false);
        System.out.println("The item has been marked as NOT done!");
        System.out.println("[ ] " + tasks.get(id).getName());
    }

    /**
     * Lists all the tasks in the task manager.
     */
    public void listTask() {
        if (tasks.size() == 0) {
            System.out.println("The list is currently empty!");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print(i + 1);
            tasks.get(i).print();
        }
    }

    /**
     * Deletes a task at the specified index.
     * 
     * @param id The index of the task to be deleted.
     */
    public void deleteTask(int id) {
        System.out.println("This item has been removed!");
        System.out.println("[ ] " + tasks.get(id).getName());
        tasks.remove(id);
    }

    /**
     * Gets the size of the task list.
     * 
     * @return The size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Clears all tasks from the task list.
     */
    public void clearData() {
        tasks.clear();
        System.out.println("Your list has been cleared !");
    }

    /**
     * Gets the task at the specified index.
     * 
     * @param id The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task getId(int id) {
        return tasks.get(id);
    }

}
