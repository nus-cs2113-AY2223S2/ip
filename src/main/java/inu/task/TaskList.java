package inu.task;

import inu.exceptionhandling.EmptyUserInputException;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> taskList;

    private final String EMPTY_STRING = "";

    private final int INDEX_OFFSET = 1;

    private final int INDEX_EMPTY_INPUT = -1;

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