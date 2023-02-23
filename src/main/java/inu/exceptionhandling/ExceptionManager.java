package inu.exceptionhandling;

import inu.task.TaskList;

/**
 * Represents methods to throw custom exceptions.
 */
public class ExceptionManager {

    /**
     * Checks for empty strings.
     *
     * @param strings the strings to be checked.
     * @throws EmptyStringException when an empty string is read.
     */
    public static void checkEmptyString(String... strings) throws EmptyStringException {
        for (String s : strings) {
            if (s.isEmpty()) {
                throw new EmptyStringException();
            }
        }
    }

    /**
     * Checks for an empty task list.
     *
     * @param taskList the tasklist to be checked
     * @throws EmptyTaskListException when the task list is empty.
     */
    public static void checkEmptyTaskList(TaskList taskList) throws EmptyTaskListException {
        if (taskList.getTaskListSize() == 0) {
            throw new EmptyTaskListException();
        }
    }

}