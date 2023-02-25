package inu.exceptionhandling;

import inu.task.TaskList;

import java.time.LocalDateTime;

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
    public static void checkValidFromAndToDate(LocalDateTime from, LocalDateTime to) throws InvalidEventFromAndToDate {
        if (from.isAfter(to)) {
            throw new InvalidEventFromAndToDate();
        }
    }

}