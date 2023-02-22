package inu.task;

import inu.commons.Messages;
import inu.exceptionhandling.EmptyStringException;
import inu.exceptionhandling.ExceptionManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskList {

    private final ArrayList<Task> taskList;

    private final String EMPTY_STRING = "";

    private final String STRING_PERIOD = ". ";

    private final int INDEX_OFFSET = 1;

    public TaskList() {
        taskList = new ArrayList<>();
    }

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

    public TaskList filterKeyWord(String keyWord) {
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

    public Task getLastTask() {
        int lastIndex = getTaskListSize() - INDEX_OFFSET;
        return taskList.get(lastIndex);
    }

}