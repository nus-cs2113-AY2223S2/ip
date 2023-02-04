package luke.command;

import luke.task.ToDo;
import luke.task.Deadline;
import luke.task.Event;
import luke.task.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class TaskOrganizer {
    /** Unique ID to be given to each new task for identification */
    private int newTaskID;

    /** A container containing all tasks added */
    private HashMap<Integer, Task> tasks;

    /** A list containing all valid task types */
    private ArrayList<String> taskTypes;

    public TaskOrganizer() {
        newTaskID = 1;
        tasks = new HashMap<Integer, Task>();
        taskTypes = new ArrayList<String>(
                Arrays.asList("todo", "event", "deadline")
        );
    }

    /**
     * Checks if the type given is a valid task type.
     *
     * @param type The type to be checked.
     * @return True if type is a valid task type, False if type is not a valid task type.
     */
    public boolean isTaskType(String type) {
        return taskTypes.contains(type);
    }

    /**
     * Checks if the index provides is out of bounds from the current available tasks.
     *
     * @param index The index provided.
     * @return True if the index provided is out of bounds, False if the index provides is not out of bounds.
     */
    public boolean isOutOfBounds(int index) {
        return (index <= 0 || index >= newTaskID);
    }

    /**
     * Checks if tasks is empty.
     *
     * @return True if tasks is empty, False if tasks is not empty.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param taskType The type of the task to be added.
     * @param taskName The name of the task to be added.
     * @param taskDate A string containing the dates of the task (E.g deadline or start and end date).
     * @return True as task have been added without any errors, False if there are errors when adding the task.
     */
    public boolean addTask(String taskType, String taskName, String taskDate) {
        Task newTask;
        switch (taskType) {
        case "todo":
            newTask = new ToDo(taskName, newTaskID);
            break;
        case "deadline":
            if (taskDate == null) {
                return false;
            }
            newTask = new Deadline(taskName, newTaskID, taskDate);
            break;
        case "event":
            if (taskDate == null) {
                return false;
            }
            String[] taskDates = taskDate.split(" ", 2);
            if (taskDates.length <= 1) {
                return false;
            }
            String startDate = taskDates[0];
            String endDate = taskDates[1];
            newTask = new Event(taskName, newTaskID, startDate, endDate);
            break;
        default:
            newTask = new Task(taskName, newTaskID);
        }
        tasks.put(newTaskID, newTask);
        newTaskID += 1;
        return true;
    }

    /**
     * Given the ID of a task, this function marks the corresponding task as completed.
     *
     * @param toMarkID The ID of the task to be marked.
     */
    public void markTask(int toMarkID) {
        tasks.get(toMarkID).markTask();
    }

    /**
     * Given the ID of a task, this function unmarks a task.
     *
     * @param toUnmarkID The ID of the task to be unmarked.
     */
    public void unmarkTask(int toUnmarkID) {
        tasks.get(toUnmarkID).unmarkTask();
    }

    /**
     * Returns the name of a task, given its ID.
     *
     * @param taskID The ID of the task.
     * @return The name of the task with taskID.
     */
    public String getTaskByID(int taskID) {
        return tasks.get(taskID).getTaskName();
    }

    /**
     * Returns an ArrayList of Tasks in the order they are added.
     *
     * @return ArrayList of Tasks.
     */
    public ArrayList<Task> getTaskList() {
        ArrayList<Task> copy = new ArrayList<Task>();
        for (int i = 1; i < newTaskID; i++) {
            copy.add(tasks.get(i));
        }
        return copy;
    }
}
