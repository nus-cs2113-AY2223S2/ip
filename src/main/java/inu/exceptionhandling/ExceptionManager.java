package inu.exceptionhandling;

import inu.task.TaskList;

public class ExceptionManager {

    public static void checkEmptyString(String... strings) throws EmptyStringException {
        for (String s : strings) {
            if (s.isEmpty()) {
                throw new EmptyStringException();
            }
        }
    }

    public static void checkEmptyTaskList(TaskList taskList) throws EmptyTaskListException {
        if (taskList.getTaskListSize() == 0) {
            throw new EmptyTaskListException();
        }
    }

}