package inu.task;

import inu.commons.Messages;
import inu.exceptionhandling.EmptyStringException;
import inu.exceptionhandling.ExceptionManager;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> taskList;

    private final String EMPTY_STRING = "";

    private final int INDEX_OFFSET = 1;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public String printList() {

        String printTaskListResult = EMPTY_STRING;

        for (int taskIndex = 0; taskIndex < taskList.size(); taskIndex++) {
            int taskNumber = taskIndex + INDEX_OFFSET;
            printTaskListResult += ("\n" + taskNumber + ". " + taskList.get(taskIndex).toString());
        }

        return printTaskListResult;

    }

    public String printListByDate(LocalDate date) {

        String printTaskListResult = EMPTY_STRING;

        for (int taskIndex = 0; taskIndex < taskList.size(); taskIndex++) {

            Task currentTask = taskList.get(taskIndex);

            if (currentTask instanceof DeadLine) {
                LocalDate byDate = ((DeadLine) currentTask).getBy().toLocalDate();
                if (byDate.equals(date)) {
                    printTaskListResult += ("\n" + taskList.get(taskIndex).toString());
                }
            } else if (currentTask instanceof Event) {
                LocalDate fromDate = ((Event) currentTask).getFrom().toLocalDate();
                LocalDate toDate = ((Event) currentTask).getTo().toLocalDate();
                if (fromDate.equals(date) || date.isBefore(toDate) || toDate.equals(date)) {
                    printTaskListResult += ("\n" + taskList.get(taskIndex).toString());
                }
            }

        }
        try {
            ExceptionManager.checkEmptyString(printTaskListResult);
            return printTaskListResult;
        } catch (EmptyStringException e) {
            return Messages.MESSAGE_NO_TASK_ON_DATE;
        }

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

    public Task getLastTask() {
        int lastIndex = getTaskListSize() - INDEX_OFFSET;
        return taskList.get(lastIndex);
    }

}