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
     * Checks that the event starting date and time occurs before its ending date
     *
     * @param from the starting date and time of the event
     * @param to the ending date and time of the event
     * @throws InvalidEventFromAndToDate when the event's starting date occurs after its ending date
     */
    public static void checkValidFromAndToDate(LocalDateTime from, LocalDateTime to) throws InvalidEventFromAndToDate {
        if (from.isAfter(to)) {
            throw new InvalidEventFromAndToDate();
        }
    }

    public static void checkCorrectDate(LocalDateTime... dates) throws InvalidDate {
        for (LocalDateTime d : dates) {
            if (d.isBefore(LocalDateTime.now())) {
                throw new InvalidDate();
            }
        }
    }

}