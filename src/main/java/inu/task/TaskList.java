package inu.task;

import inu.commons.Messages;
import inu.exceptionhandling.EmptyStringException;
import inu.exceptionhandling.ExceptionManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represents the task list and methods to manage the task list.
 */
public class TaskList {

    private final ArrayList<Task> taskList;

    private final String EMPTY_STRING = "";

    private final String STRING_PERIOD = ". ";

    private final int INDEX_OFFSET = 1;

    /**
     * Constructor for a new task list.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Displays all tasks in a given task list in the order they were added.
     *
     * @param taskList the given task list to display as a string.
     * @return the task list as a string
     */
    public String printList(TaskList taskList) {
        String printTaskListResult = EMPTY_STRING;
        for (int taskIndex = 0; taskIndex < taskList.getTaskListSize(); taskIndex++) {
            int taskNumber = taskIndex + INDEX_OFFSET;
            String currentTaskDisplay = taskList.getTask(taskIndex).toString();
            printTaskListResult += ("\n" + taskNumber + STRING_PERIOD + currentTaskDisplay);
        }
        try {
            ExceptionManager.checkEmptyString(printTaskListResult);
            return printTaskListResult;
        } catch (EmptyStringException e) {
            return Messages.MESSAGE_PROMPT_EMPTY_TASK_LIST;
        }
    }

    /**
     * Create a new task list containing tasks that occur on a specified date.
     *
     * @param date the date to query.
     * @return a new task list with the filtered tasks.
     */
    public TaskList filterDate(LocalDate date) {
        TaskList dateTaskList = new TaskList();
        for (Task currentTask : taskList) {
            if (currentTask instanceof DeadLine) {
                LocalDate byDate = ((DeadLine) currentTask).getBy().toLocalDate();
                if (byDate.equals(date)) {
                    dateTaskList.addTask(currentTask);
                }
            } else if (currentTask instanceof Event) {
                LocalDate fromDate = ((Event) currentTask).getFrom().toLocalDate();
                LocalDate toDate = ((Event) currentTask).getTo().toLocalDate();
                if (fromDate.equals(date) || (date.isAfter(fromDate) && date.isBefore(toDate)) || toDate.equals(date)) {
                    dateTaskList.addTask(currentTask);
                }
            }
        }
        return dateTaskList;
    }

    /**
     * Create a new task list containing tasks that contain a specific key word.
     *
     * @param keyWord the key word to query.
     * @return a new task list with the filtered tasks.
     */
    public TaskList filterKeyWord(String keyWord) {
        // Use of Streams
        ArrayList<Task> streamKeyWordTaskList = taskList
                .stream()
                .filter(c -> c.getDescription().contains(keyWord))
                .collect(Collectors.toCollection(ArrayList::new));
        TaskList keyWordTaskList = new TaskList();
        for (Task t : streamKeyWordTaskList) {
            keyWordTaskList.addTask(t);
        }
        return keyWordTaskList;
    }

    public void addTask(Task t) {
        taskList.add(t);
    }

    public void markTask(int index) {
        taskList.get(index).setDone();
    }

    public void unMarkTask(int index) {
        taskList.get(index).resetDone();
    }

    public void deleteTask(int index) {
        taskList.remove(index);
        taskList.trimToSize();
    }

    public int getTaskListSize() {
        return taskList.size();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Gets the most recent task added to the task list
     *
     * @return the most recent task
     */
    public Task getLastTask() {
        int lastIndex = getTaskListSize() - INDEX_OFFSET;
        return taskList.get(lastIndex);
    }

}