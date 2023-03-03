package inu.exceptionhandling;

import java.time.LocalDateTime;

/**
 * Represents methods to throw custom exceptions.
 */
public class ExceptionManager {

    /**
     * Checks for empty strings.
     *
     * @param string the strings to be checked.
     * @throws EmptyStringException when an empty string is read.
     */
    public static void checkEmptyString(String string) throws EmptyStringException {
        if (string.isEmpty()) {
            throw new EmptyStringException();
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

    /**
     * Checks that the date provided for tasks is not in the past
     *
     * @param date provided from user input
     * @throws InvalidDate when the date provided is in the past, hence it is invalid.
     */
    public static void checkCorrectDate(LocalDateTime date) throws InvalidDate {
        if (date.isBefore(LocalDateTime.now())) {
            throw new InvalidDate();
        }
    }
}