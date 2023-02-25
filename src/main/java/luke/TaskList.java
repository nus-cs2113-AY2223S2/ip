package luke;

import luke.task.ToDo;
import luke.task.Deadline;
import luke.task.Event;
import luke.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * A <code>TaskList</code> object is created to handle operations like adding, removing, marking, unmarking and finding
 * of tasks.
 */
public class TaskList implements StringManipulation {
    /** Unique ID to be given to each new task for identification */
    private int newTaskID;

    /** A container containing all tasks added */
    private HashMap<Integer, Task> tasks;

    /** A dictionary that maps the serial number of the task list printed out to the taskID */
    private HashMap<Integer, Integer> serialNumbers;

    /** Default time if the user did not key in a time when adding a deadline or event */
    private static final String DEFAULT_TIME = "00:00";

    /** A list containing all valid task types */
    private static final ArrayList<String> TASK_TYPES = new ArrayList<String>(
            Arrays.asList("todo", "event", "deadline")
    );
    ;

    /** Default constructor when there are no previously saved data */
    public TaskList() {
        this.newTaskID = 1;
        this.tasks = new HashMap<Integer, Task>();
        this.serialNumbers = new HashMap<Integer, Integer>();
    }

    /** Constructor used when there are previously saved data */
    public TaskList(int newTaskID, HashMap<Integer, Task> savedTaskOrders, HashMap<Integer, ToDo> savedToDos,
                    HashMap<Integer, Deadline> savedDeadlines, HashMap<Integer, Event> savedEvents,
                    HashMap<Integer, Integer> savedSerialNumbers) {
        this.newTaskID = newTaskID;
        this.serialNumbers = savedSerialNumbers;
        this.tasks = new HashMap<Integer, Task>();

        // Repopulate map with the correct data types.
        for (Map.Entry<Integer, Task> entry : savedTaskOrders.entrySet()) {
            Task task = entry.getValue();
            String taskLabel = task.getTaskLabel();
            int taskID = task.getTaskID();
            switch (taskLabel) {
            case "[T]":
                tasks.put(taskID, savedToDos.get(taskID));
                break;
            case "[D]": ;
                tasks.put(taskID, savedDeadlines.get(taskID));
                break;
            default:
                tasks.put(taskID, savedEvents.get(taskID));
            }
        }
    }

    public void resetTaskList() {
        this.newTaskID = 1;
        this.tasks.clear();
        this.serialNumbers.clear();
    }

    /**
     * Checks if the type given is a valid task type.
     *
     * @param type The type to be checked.
     * @return True if type is a valid task type, False if type is not a valid task type.
     */
    public boolean isTaskType(String type) {
        return TASK_TYPES.contains(type);
    }

    /**
     * Checks if the index provides is out of bounds from the current available tasks.
     *
     * @param index The index provided.
     * @return True if the index provided is out of bounds, False if the index provides is not out of bounds.
     */
    public boolean isOutOfBounds(int index) {
        return (index <= 0 || index > tasks.size());
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
     * This method takes an input date and formats it so that it can be parsed into a LocalDateTime object
     * If the method cannot find a time component in the date, it adds in the default time of 00:00.
     *
     * @param date The input date string to be formatted.
     * @return A string that can be parsed into a LocalDateTime object.
     */
    private String formatDate(String date) {
        String dateString = StringManipulation.getFirstWord(date.trim());
        String timeString= StringManipulation.removeFirstWord(date.trim());
        if (timeString == null) {
            return String.format(dateString + "T" + DEFAULT_TIME);
        }
        return String.format(dateString + "T" + timeString);
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
            String formattedDeadline = formatDate(taskDate);
            try {
                LocalDateTime.parse(formattedDeadline);
                newTask = new Deadline(taskName, newTaskID, formattedDeadline);
            } catch (DateTimeParseException e) {
                return false;
            }
            break;
        default:
            if (taskDate == null) {
                return false;
            }
            String startDateString = StringManipulation.getFirstDetail(taskDate);
            String endDateString = StringManipulation.removeFirstDetail(taskDate);
            if (startDateString == null || endDateString == null) {
                return false;
            }
            String formattedStartDate = formatDate(startDateString);
            String formattedEndDate = formatDate(endDateString);

            try {
                LocalDateTime.parse(formattedStartDate);
                LocalDateTime.parse(formattedEndDate);
                newTask = new Event(taskName, newTaskID, formattedStartDate, formattedEndDate);
            } catch (DateTimeParseException e) {
                return false;
            }
            break;
        }
        tasks.put(newTaskID, newTask);
        newTaskID += 1;
        updateSerialNumbers();
        return true;
    }

    /**
     * Given the serial number of a task, this function marks the corresponding task as completed.
     *
     * @param toMarkSerialNumber The serial number of the task to be marked.
     */
    public void markTask(int toMarkSerialNumber) {
        int toMarkID = serialNumbers.get(toMarkSerialNumber);
        tasks.get(toMarkID).markTask();
    }

    /**
     * Given the serial number of a task, this function unmarks a task.
     *
     * @param toUnmarkSerialNumber The serial number of the task to be unmarked.
     */
    public void unmarkTask(int toUnmarkSerialNumber) {
        int toUnmarkID = serialNumbers.get(toUnmarkSerialNumber);
        tasks.get(toUnmarkID).unmarkTask();
    }

    /**
     * Given the ID of a task, this function deletes a task.
     *
     * @param toDeleteSerialNumber The ID of the task to be deleted.
     */
    public void deleteTask(int toDeleteSerialNumber) {
        int toDeleteID = serialNumbers.get(toDeleteSerialNumber);
        tasks.remove(toDeleteID);
        updateSerialNumbers();
    }

    public ArrayList<Task> findTask(String toFind) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
            Task task = entry.getValue();
            if (task.getTaskName().contains(toFind)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }

    /**
     * Returns the name of a task, given its serial number.
     *
     * @param taskSerialNumber The serial number of the task.
     * @return The name of the task with taskSerialNumber.
     */
    public String getTaskBySerialNumber(int taskSerialNumber) {
        int taskID = serialNumbers.get(taskSerialNumber);
        return tasks.get(taskID).getTaskName();
    }

    /** Updates the serialNumbers to match the current IDs of the tasks */
    private void updateSerialNumbers() {
        int serialNumber = 1;
        for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
            Task task = entry.getValue();
            task.updateSerialNumber(serialNumber);
            serialNumbers.put(serialNumber, task.getTaskID());
            serialNumber += 1;
        }
    }

    /**
     * Returns an ArrayList of Tasks in the order they are added.
     *
     * @return ArrayList of Tasks.
     */
    public ArrayList<Task> getTaskList() {
        ArrayList<Task> printTasks = new ArrayList<Task>();
        for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
            Task task = entry.getValue();
            printTasks.add(task);
        }
        return printTasks;
    }

    public HashMap<Integer, Task> getTasks() {
        return tasks;
    }

    public int getNewTaskID() {
        return newTaskID;
    }

    public HashMap<Integer, Integer> getSerialNumbers() {
        return serialNumbers;
    }
}
