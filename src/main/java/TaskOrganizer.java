import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class TaskOrganizer {
    /** Unique ID to be given to each new task for identification */
    private int newTaskID;

    /** A container containing all tasks added */
    private HashMap<Integer, Task> tasks;

    public TaskOrganizer() {
        newTaskID = 1;
        tasks = new HashMap<Integer, Task>();
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
     * @param taskName The name of the task to be added to the list tasks.
     */
    public String addTask(String taskInfo) {
        String[] taskInfos = taskInfo.split(" ", 2);
        String taskType = taskInfos[0];
        String taskName = taskInfos[1];
        Task newTask;
        switch (taskType) {
        case "todo":
            newTask = new ToDo(taskName, newTaskID);
            break;
        case "deadline":
            String[] temp = taskName.split(" ", 2);
            String taskName2 = temp[0];
            String deadlineDate = temp[1];
            newTask = new Deadline(taskName2, newTaskID, deadlineDate);
            break;
        case "event":
            String[] temp2 = taskName.split(" ", 3);
            String taskName3 = temp2[0];
            String startDateEvent = temp2[1];
            String endDateEvent = temp2[2];
            newTask = new Event(taskName3, newTaskID, startDateEvent, endDateEvent);
            break;
        default:
            newTask = new Task(taskName, newTaskID);
        }
        tasks.put(newTaskID, newTask);
        newTaskID += 1;
        return taskName;
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
