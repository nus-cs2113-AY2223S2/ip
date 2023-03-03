package inu.commons;

import inu.exceptionhandling.EmptyStringException;
import inu.exceptionhandling.ExceptionManager;
import inu.exceptionhandling.InvalidDateFormat;
import inu.exceptionhandling.InvalidDateTimeFormat;
import inu.task.TaskList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility methods.
 */
public class Util {
    public static final int INDEX_BEGIN = 0;
    public static final int INDEX_OFFSET_IN_COMMAND = 1;
    public static final int INDEX_OFFSET_IN_BY_COMMAND = 5;
    public static final int INDEX_OFFSET_IN_FROM_COMMAND = 7;
    public static final int INDEX_OFFSET_IN_TO_COMMAND = 5;
    public static final String DELIMITER_DEADLINE_BY = " /by ";
    public static final String DELIMITER_EVENT_FROM = " /from ";
    public static final String DELIMITER_EVENT_TO = " /to ";

    /**
     * Reads in a string of numerical value and converts it into a task list index as an integer.
     * Checks if index provided is within the range of the number of tasks.
     *
     * @param taskList the task list the index accesses
     * @param userString the index input in the form of a String to be converted into the task list index
     * @return task index as an integer converted from the given String
     */
    public static int fetchIndexFromString(TaskList taskList, String userString)
            throws EmptyStringException, IndexOutOfBoundsException {
        ExceptionManager.checkEmptyString(userString);
        int actualIndex = Integer.parseInt(userString) - INDEX_OFFSET_IN_COMMAND;
        int taskListSize = taskList.getTaskListSize();
        if (actualIndex < INDEX_BEGIN || actualIndex >= taskListSize) {
            throw new IndexOutOfBoundsException();
        } else {
            return actualIndex;
        }
    }

    /**
     * Fetches the task description before the delimiter as a string
     *
     * @param userString the String containing full task details
     * @param delimiter the String delimiter that the task description appears before
     * @return task description as a String
     */
    public static String fetchTask(String userString, String delimiter) throws EmptyStringException {
        int indexOfFirstSlash = userString.indexOf(delimiter);
        String task = userString.substring(INDEX_BEGIN, indexOfFirstSlash);
        ExceptionManager.checkEmptyString(task);
        return task;
    }

    /**
     * Fetches the deadline due date from the full task description
     *
     * @param userString the String containing full task details
     * @return the deadline due date as a String
     */
    public static String fetchBy(String userString) throws EmptyStringException {
        int firstSlashEntry = userString.indexOf(DELIMITER_DEADLINE_BY);
        String by = userString.substring(firstSlashEntry + INDEX_OFFSET_IN_BY_COMMAND);
        ExceptionManager.checkEmptyString(by);
        return by;
    }

    /**
     * Fetches the event start date from the full task description
     *
     * @param userString the String containing full task details
     * @return the event start date as a String
     */
    public static String fetchFrom(String userString) throws EmptyStringException {
        int firstSlashEntry = userString.indexOf(DELIMITER_EVENT_FROM);
        int secondSlashEntry = userString.indexOf(DELIMITER_EVENT_TO);
        String from = userString.substring(firstSlashEntry + INDEX_OFFSET_IN_FROM_COMMAND, secondSlashEntry);
        ExceptionManager.checkEmptyString(from);
        return from;
    }

    /**
     * Fetches the event end date from the full task description
     *
     * @param userString the String containing full task details
     * @return the event end date as a String
     */
    public static String fetchTo(String userString) throws EmptyStringException {
        int secondSlashEntry = userString.indexOf(DELIMITER_EVENT_TO);
        String to = userString.substring(secondSlashEntry + INDEX_OFFSET_IN_TO_COMMAND);
        ExceptionManager.checkEmptyString(to);
        return to;
    }

    /**
     * Parses the string and converts to a LocalDate object.
     *
     * @param localDate string to be converted to date (format: yyyy-MM-dd)
     * @return LocalDate converted from the given string
     */
    public static LocalDate parseDate(String localDate) throws EmptyStringException, InvalidDateFormat {
        try {
            ExceptionManager.checkEmptyString(localDate);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(localDate, formatter);
        } catch (DateTimeParseException ignored) {
            throw new InvalidDateFormat();
        }
    }

    /**
     * Parses the string and converts to a LocalDateTime object.
     *
     * @param localDateTime string to be converted to date (format: yyyy-MM-dd)
     * @return LocalDateTime converted from the given string
     */
    public static LocalDateTime parseDateTime(String localDateTime) throws InvalidDateTimeFormat {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            return LocalDateTime.parse(localDateTime, formatter);
        } catch (DateTimeParseException ignored) {
            throw new InvalidDateTimeFormat();
        }
    }

    /**
     * Parses the LocalDate and converts to a string.
     *
     * @param localDate date (format: yyyy-MM-dd) to be converted into string (format: "d MMM yyyy")
     * @return string converted from the given LocalDate
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html">
     *     DateTimeFormatter</a>
     */
    public static String convertDateToString(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        return localDate.format(formatter);
    }

    /**
     * Parses the LocalDateTime and converts to a string.
     *
     * @param localDateTime date (format: yyyy-MM-dd) to be converted into string (format: "d MMM yyyy hh:mm a")
     * @return string converted from the given LocalDateTime
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html">
     *     DateTimeFormatter</a>
     */
    public static String convertDateTimeToString(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a");
        return localDateTime.format(formatter);
    }
}