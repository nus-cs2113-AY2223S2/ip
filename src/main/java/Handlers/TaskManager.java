package Handlers;

import Tasks.Task;
import java.util.ArrayList;

import Exceptions.EmptyListException;

public abstract class TaskManager {

    private static ArrayList<Task> taskList = new ArrayList<>();

    public static ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Adds a task to the taskList Array
     * 
     * @param t
     *            the task to be added
     */
    public static void addTask(Task t) {
        taskList.add(t);
    }

    /**
     * Deletes a task from the taskList Array
     * 
     * @param index
     */
    public static void deleteTask(int index) {
        Ui.deletedTaskMessage(index);
        taskList.remove(index - 1);
    }

    public static int getTaskCount() {
        return taskList.size();
    }

    /**
     * Prints out all tasks in the taskList Array for the user to read.
     */
    public static void listTask() {
        try {
            printTasksFromArray();
        } catch (EmptyListException e) {
            Ui.emptyTaskListMessage();
        }
    }

    /**
     * Checks if the taskList Array is empty and prints out the tasks in the
     * taskList Array.
     * 
     * @throws EmptyListException
     *             if the taskList Array is empty
     */
    private static void printTasksFromArray() throws EmptyListException {

        if (taskList.isEmpty()) {
            throw new EmptyListException();
        }

        int existingTaskCount = 1;
        for (Task item : taskList) {
            Ui.describeTaskMessage(existingTaskCount, item);
            existingTaskCount++;
        }

        Ui.taskCountMessage();
    }

    /**
     * Takes in the user input index, converts to the index of the task in the
     * taskList Array and
     * marks it as done.
     * 
     * @param index
     *            the index of the task in the taskList Array
     */
    public static void markTask(int index) {
        if (index > 0 && index <= getTaskCount()) {
            ArrayList<Task> list = getTaskList();
            Task task = list.get(index - 1);
            task.markAsDone();
            Ui.markTaskMessage(task);
        }
    }

    /**
     * Takes in the user input index, converts to the index of the task in the
     * taskList Array and
     * marks it as not done.
     * 
     * @param index
     *            the index of the task in the taskList Array
     */
    public static void unmarkTask(int index) {
        if (index > 0 && index <= getTaskCount()) {
            ArrayList<Task> list = getTaskList();
            Task task = list.get(index - 1);
            task.unmarkAsDone();
            Ui.unmarkTaskMessage(task);
        }
    }

    /**
     * Prints out all tasks that contain the given description.
     * 
     * @param description
     *            the description to be searched for
     */
    public static void findTask(String description) {
        ArrayList<Task> list = getTaskList();
        int existingTaskCount = 1;
        for (Task item : list) {
            if (item.describeTask().contains(description)) {
                Ui.describeTaskMessage(existingTaskCount, item);
                existingTaskCount++;
            }
        }
    }

    /**
     * Reads a single task from the file and adds it to the task list
     * and input the relevant details depending on the task type.
     * 
     * @param line
     *            the line of text from the file
     */
    public static void readTaskFromFile(String line) {
        String[] taskAllDetails = line.split("\\|");
        String taskType = taskAllDetails[0].trim();
        String taskStatus = taskAllDetails[1].trim();
        String taskDescription = taskAllDetails[2].trim();

        switch (taskType) {
        case "T":
            addTodoTask(taskStatus, taskDescription);
            break;
        case "D":
            addDeadlineTask(taskAllDetails, taskStatus, taskDescription);
            break;
        case "E":
            addEventTask(taskAllDetails, taskStatus, taskDescription);
            break;
        default:
            Ui.fileErrorMessage();
            break;
        }
    }

    /**
     * Checks if todo task is done and adds it to the task list.
     * 
     * @param taskStatus
     *            the status of the task
     * @param taskDescription
     *            the description of the task
     */
    private static void addTodoTask(String taskStatus, String taskDescription) {
        Task todo = new Tasks.Todo(taskDescription);
        if (isTaskDone(taskStatus)) {
            todo.markAsDone();
        }
        TaskManager.addTask(todo);
    }

    /**
     * Checks if the deadline task is done and adds it to the task list.
     * 
     * @param taskDetails
     *            the full details of the task
     * @param taskStatus
     *            the status of the task
     * @param taskDescription
     *            the description of the task
     */
    private static void addDeadlineTask(String[] taskDetails, String taskStatus, String taskDescription) {
        String taskDeadline = taskDetails[3].trim();
        Task deadline = new Tasks.Deadline(taskDescription, taskDeadline);
        if (isTaskDone(taskStatus)) {
            deadline.markAsDone();
        }
        TaskManager.addTask(deadline);
    }

    /**
     * Checks if the event task is done and adds it to the task list.
     * 
     * @param taskDetails
     *            the full details of the task
     * @param taskStatus
     *            the status of the task
     * @param taskDescription
     *            the description of the task
     */
    private static void addEventTask(String[] taskDetails, String taskStatus, String taskDescription) {
        String[] taskDate = taskDetails[3].split("-");
        String taskDateFrom = taskDate[0].trim();
        String taskDateTo = taskDate[1].trim();
        Task event = new Tasks.Event(taskDescription, taskDateFrom, taskDateTo);
        if (isTaskDone(taskStatus)) {
            event.markAsDone();
        }
        TaskManager.addTask(event);
    }

    /**
     * Checks if the task is done.
     * 
     * @param taskStatus
     *            the status of the task
     * @return true if the task is done, false otherwise
     */
    private static boolean isTaskDone(String taskStatus) {
        return taskStatus.equals("1");
    }
}
