package inu.commons;

import inu.exceptionhandling.EmptyStringException;
import inu.exceptionhandling.InvalidDateFormat;
import inu.exceptionhandling.InvalidDateTimeFormat;
import inu.task.TaskList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {

    public static final int INDEX_BEGIN = 0;

    public static final int INDEX_OFFSET_IN_COMMAND = 1;

    public static final int INDEX_OFFSET_IN_BY_COMMAND = 5;

    public static final int INDEX_OFFSET_IN_FROM_COMMAND = 7;

    public static final int INDEX_OFFSET_IN_TO_COMMAND = 5;

    public static final String DELIMITER_DEADLINE_BY = " /by ";

    public static final String DELIMITER_EVENT_FROM = " /from ";

    public static final String DELIMITER_EVENT_TO = " /to ";

    public static int fetchIndexFromString(TaskList taskList, String userString) throws IndexOutOfBoundsException {
        int actualIndex = Integer.parseInt(userString) - INDEX_OFFSET_IN_COMMAND;
        int taskListSize = taskList.getTaskListSize();
        if (actualIndex < INDEX_BEGIN || actualIndex >= taskListSize) {
            throw new IndexOutOfBoundsException();
        } else {
            return actualIndex;
        }
    }

    public static String fetchTask(String userString, String delimiter) {
        int indexOfFirstSlash = userString.indexOf(delimiter);
        String task = userString.substring(INDEX_BEGIN, indexOfFirstSlash);
        return task;
    }

    public static String fetchBy(String userString) {
        int firstSlashEntry = userString.indexOf(DELIMITER_DEADLINE_BY);
        String by = userString.substring(firstSlashEntry + INDEX_OFFSET_IN_BY_COMMAND);
        return by;
    }

    public static String fetchFrom(String userString) throws EmptyStringException {
        int firstSlashEntry = userString.indexOf(DELIMITER_EVENT_FROM);
        int secondSlashEntry = userString.indexOf(DELIMITER_EVENT_TO);
        if (firstSlashEntry == secondSlashEntry) {
            throw new EmptyStringException();
        } else {
            String from = userString.substring(firstSlashEntry + INDEX_OFFSET_IN_FROM_COMMAND, secondSlashEntry);
            return from;
        }
    }

    public static String fetchTo(String userString) {
        int secondSlashEntry = userString.indexOf(DELIMITER_EVENT_TO);
        String to = userString.substring(secondSlashEntry + INDEX_OFFSET_IN_TO_COMMAND);
        return to;
    }

    public static LocalDate parseDate(String localDate) throws InvalidDateFormat {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(localDate, formatter);
        } catch (DateTimeParseException ignored) {
            throw new InvalidDateFormat();
        }
    }

    public static LocalDateTime parseDateTime(String localDateTime) throws InvalidDateTimeFormat {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            return LocalDateTime.parse(localDateTime, formatter);
        } catch (DateTimeParseException ignored) {
            throw new InvalidDateTimeFormat();
        }
    }

    public static String convertDateToString(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        return localDate.format(formatter);
    }

    public static String convertDateTimeToString(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a");
        return localDateTime.format(formatter);
    }

}