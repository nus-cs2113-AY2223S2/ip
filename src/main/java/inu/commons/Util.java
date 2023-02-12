package inu.commons;

import inu.exceptionhandling.EmptyUserInputException;
import inu.task.TaskList;

public class Util {

    protected static final int INDEX_BEGIN = 0;

    protected static final int INDEX_OFFSET_IN_COMMAND = 1;

    public static int fetchIndexFromString(String userString) {
        return Integer.parseInt(userString) - INDEX_OFFSET_IN_COMMAND;
    }

    public static void markTask(TaskList taskList, int taskIndex) {
        taskList.getTask(taskIndex).setDone();
    }

    public static void unMarkTask(TaskList taskList, int taskIndex) {
        taskList.getTask(taskIndex).resetDone();
    }

    public static String fetchTask(String userString) {
        int indexOfFirstSlash = userString.indexOf("/");
        return userString.substring(INDEX_BEGIN, indexOfFirstSlash);
    }

    public static String fetchDeadLine(String userString) {
        int firstSlashEntry = userString.indexOf("/");
        return userString.substring(firstSlashEntry + INDEX_OFFSET_IN_COMMAND);
    }

    public static String fetchFrom(String userString) throws EmptyUserInputException {
        int firstSlashEntry = userString.indexOf("/");
        int secondSlashEntry = userString.lastIndexOf("/");
        if (firstSlashEntry != secondSlashEntry) {
            return userString.substring(firstSlashEntry + INDEX_OFFSET_IN_COMMAND, secondSlashEntry);
        }
        throw new EmptyUserInputException();
    }

    public static String fetchTo(String userString) {
        int secondSlashEntry = userString.lastIndexOf("/");
        return userString.substring(secondSlashEntry + INDEX_OFFSET_IN_COMMAND);
    }

}